package net.dimensionshift.mod.ai;

import java.util.List;
import java.util.Random;

import net.dimensionshift.mod.api.IMobHungry;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemFood;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;

public class EntityAIFood extends EntityAIBase {
	private static final IEntitySelector foodEntitySelector = new IEntitySelector() {
		/**
		 * Return whether the specified entity is applicable to this filter.
		 */
		@Override
		public boolean isEntityApplicable(Entity entity) {
			if (entity instanceof EntityItem) {
				EntityItem entityItem = (EntityItem) entity;
				if (entityItem.getEntityItem().getItem() instanceof ItemFood) {
					return true;
				}
			}
			return false;
		}
	};

	/** The entity using this AI that is tempted by the player. */
	private EntityCreature attractedEntity;

	private int range = 0;

	private int spawnChance = -1;
	private int spawnAmount = 1;
	private int spawnAmountMax = 2;

	private EntityItem attractingItem;

	private double speed;
	/** X position of item tempting this mob */
	private double targetX;
	/** Y position of item tempting this mob */
	private double targetY;
	/** Z position of item tempting this mob */
	private double targetZ;

	/** True if this EntityAITempt task is running */
	private boolean isRunning;
	private boolean avoidsWaterStandart;

	public EntityAIFood(EntityCreature entity, int range, double speed, int spawnChanceOnFed) {
		this(entity, range, speed, spawnChanceOnFed, 0, 2);
	}

	public EntityAIFood(EntityCreature entity, int range, double speed, int spawnChanceOnFed, int spawnMinAmount, int spawnMaxAmount) {
		this.range = range;
		this.attractedEntity = entity;
		this.speed = speed;
		this.spawnChance = spawnChanceOnFed;

		if (spawnMinAmount < 1) spawnMinAmount = 1;
		this.spawnAmount = spawnMinAmount;
		if (spawnMaxAmount < spawnMinAmount) spawnMaxAmount = spawnMinAmount + 1;
		this.spawnAmountMax = spawnMaxAmount;

	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {
		// Checking if hungry
		if (this.attractedEntity instanceof IMobHungry) {
			if (((IMobHungry) this.attractedEntity).getHunger() > 0) {
				return false;
			}
		}

		@SuppressWarnings("unchecked")
		List<EntityItem> items = this.attractedEntity.worldObj.selectEntitiesWithinAABB(EntityItem.class, this.attractedEntity.boundingBox.expand(range, 4.0D, range), EntityAIFood.foodEntitySelector);
		if (items.size() == 0) {
			return false;
		} else {
			EntityItem item = items.get(0);

			for (int i = 0; i < items.size(); i++) {
				if (this.attractedEntity.getDistanceSqToEntity(items.get(i)) < this.attractedEntity.getDistanceSqToEntity(item)) {
					item = items.get(i);
				}
			}
			this.attractingItem = item;

			return true;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting() {
		if (this.attractingItem != null) {
			this.targetX = this.attractingItem.posX;
			this.targetY = this.attractingItem.posY;
			this.targetZ = this.attractingItem.posZ;
		}

		if (this.attractedEntity.getNavigator().noPath()) {
			return false;
		}

		return this.shouldExecute();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting() {

		this.targetX = this.attractingItem.posX;
		this.targetY = this.attractingItem.posY;
		this.targetZ = this.attractingItem.posZ;
		this.isRunning = true;
		this.avoidsWaterStandart = this.attractedEntity.getNavigator().getAvoidsWater();
		this.attractedEntity.getNavigator().setAvoidsWater(true);
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask() {
		this.attractingItem = null;
		this.attractedEntity.getNavigator().clearPathEntity();
		this.isRunning = false;
		this.attractedEntity.getNavigator().setAvoidsWater(this.avoidsWaterStandart);
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask() {

		if (this.attractedEntity.getDistanceSqToEntity(this.attractingItem) < 0.35) {

			Random random = new Random();

			if (this.attractingItem.getEntityItem().hasTagCompound()) {
				NBTTagCompound nbt = this.attractingItem.getEntityItem().getTagCompound();
				if (nbt.getBoolean("dimensionshift.isEffectApplied")) {
					int[] potionIds = nbt.getIntArray("dimensionshift.effectType");
					int[] potionDuration = nbt.getIntArray("dimensionshift.effectDuration");
					for (int i = 0; i < potionIds.length; i++) {
						this.attractedEntity.addPotionEffect(new PotionEffect(potionIds[i], potionDuration[i]));
					}
				}
			}

			if (random.nextInt(20) <= 1) {
				this.attractingItem.getEntityItem().stackSize--;
			}

			if (!this.attractedEntity.isDead) {
				if (random.nextInt(100) <= spawnChance && !this.attractedEntity.worldObj.isRemote) {

					Entity entity = EntityList.createEntityByName(EntityList.getEntityString(this.attractedEntity), this.attractedEntity.worldObj);
					entity.setPosition(this.attractedEntity.posX, this.attractedEntity.posY, this.attractedEntity.posZ);

					((IMobHungry) entity).setHunger(((IMobHungry) entity).getFullHunger());
					for (int i = 0; i < this.spawnAmount + random.nextInt((this.spawnAmountMax - this.spawnAmount)); i++) {
						this.attractedEntity.worldObj.spawnEntityInWorld(entity);
					}

				}
			}

			((IMobHungry) this.attractedEntity).setHunger(((IMobHungry) this.attractedEntity).getFullHunger());

			this.attractedEntity.getNavigator().clearPathEntity();
			// this.resetTask();

		} else {
			this.attractedEntity.getNavigator().tryMoveToEntityLiving(this.attractingItem, this.speed);

		}
	}

	/**
	 * @see #isRunning
	 */
	public boolean isRunning() {
		return this.isRunning;
	}
}