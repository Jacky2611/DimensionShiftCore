package net.dimensionshift.mod.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.DimensionShiftItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;

import org.apache.commons.lang3.ArrayUtils;


public class EventHandlerDimensionShift {

	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event) {

		Entity entity = event.entityLiving;

		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;

			// triggering ender dust achievement if he has the item ender Dust
			if (player.inventory.hasItem(DimensionShiftItems.itemEnderDust)) {
				player.addStat(DimensionShift.achievementEnderDust, 1);
			}

			if (player.isEating()) {

				ItemStack item = player.getCurrentEquippedItem();

				if (item != null && item.getItem() != null) {
					if ((item.getItem()) instanceof ItemFood && item.hasTagCompound()) {
						NBTTagCompound nbt = item.getTagCompound();
						if (nbt.getBoolean("dimensionshift.isEffectApplied")) {

							int[] potionIds = nbt.getIntArray("dimensionshift.effectType");
							int[] potionDuration = nbt.getIntArray("dimensionshift.effectDuration");

							for (int i = 0; i < potionIds.length; i++) {
								player.addPotionEffect(new PotionEffect(potionIds[i], potionDuration[i]));
							}

						}
					}
				}
			}
		}

	}

	@SubscribeEvent
	public void PlayerChangedDimensionEvent(PlayerChangedDimensionEvent event) {
		EntityPlayer player = event.player;
		World worldTo = MinecraftServer.getServer().worldServerForDimension(event.toDim);
		World worldFrom = MinecraftServer.getServer().worldServerForDimension(event.fromDim);

		NBTTagCompound tag = player.getEntityData();
		NBTBase tagBase = tag.getTag("UsedTeleporter");
		Random random = new Random();

		// armor Boost(0 - 40 *4)
		int armorBoost = 0;

		for (int i = 0; i < 4; i++) {
			ItemStack armor = player.inventory.armorInventory[i];
			if (armor != null && armor.isItemEnchanted()) {
				int level = EnchantmentHelper.getEnchantmentLevel(DimensionShift.enchantmentDimensionTravelingStabilisation.effectId, armor);
				if (level > 0) {
					armorBoost = armorBoost + level * 10;
				}
			}
		}

		if (tagBase != null && tag.getInteger("UsedTeleporter") > 0 && !player.capabilities.isCreativeMode) {

			/*
			 * DID USE ONE OF MY TELEPORTERS
			 */

			// Variable how stable the teleporter is. 1 unstable, 10 extremly
			// stable
			int teleporter = tag.getInteger("UsedTeleporter");

			// if you wear a full enchantmed armor you get an additional
			// teleporter level up
			int higherTeleporterChance = 0;
			for (int i = 0; i < 4; i++) {
				ItemStack armor = player.inventory.armorInventory[i];
				if (armor != null && armor.isItemEnchanted()) {
					int level = EnchantmentHelper.getEnchantmentLevel(DimensionShift.enchantmentDimensionTravelingStabilisation.effectId, armor);
					if (level > 0) {
						higherTeleporterChance = armorBoost + level * 10;
					}
				}
			}
			if ((higherTeleporterChance / 160 * 100) >= random.nextInt(100)) {
				teleporter++;
			}

			if (teleporter == 1) {

				player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 200 - armorBoost));
				player.setHealth(player.getHealth() / (4 / (armorBoost / 10)));
				if (random.nextInt(10) > 8) {
					player.setDead();
				}

			} else if (teleporter == 2) {
				player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 200 - armorBoost));
				player.setHealth(player.getHealth() / (4 / (armorBoost / 10)));
			} else if (teleporter == 3) {
				player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 380 - armorBoost * 2));
				player.addPotionEffect(new PotionEffect(Potion.wither.getId(), 180 - armorBoost));
			} else if (teleporter == 4) {
				player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 340 - armorBoost * 2));
				player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 220 - armorBoost * 2));
				player.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 120 - armorBoost));
			} else if (teleporter == 5) {
				player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 300 - armorBoost));
				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 320 - armorBoost));

			} else if (teleporter == 6) {

				player.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 280 - armorBoost));
				player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 220 - armorBoost));

			} else if (teleporter == 7) {

				player.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 160 - armorBoost));
				player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 220 - armorBoost));

			} else if (teleporter == 8) {

				player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 160 - armorBoost));

			} else if (teleporter == 9) {
				player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 80 - armorBoost));
			} else if (teleporter > 9) {

			}

		} else if (!player.capabilities.isCreativeMode) {

			/*
			 * DID NOT USE ONE OF MY TELEPORTERS
			 */

			int number = random.nextInt(7);

			if ((armorBoost / 160 * 130) >= random.nextInt(100)) {
				number++;

			}

			if (number == 0) {
				// Clears Inventory and kills Player

				IInventory inv = player.inventory;
				for (int i = 0; i < inv.getSizeInventory(); i++) {
					if (inv.getStackInSlot(i) != null) {
						ItemStack j = inv.getStackInSlot(i);
						if (j.getItem() != null) {
							inv.setInventorySlotContents(i, null);
							System.out.println("removed item");
						}
					}
				}

				player.setHealth(0);

			} else if (number == 1) {

				// Kills Player
				player.setHealth(0);

			} else if (number == 2) {
				// removing random items from inventory
				IInventory inv = player.inventory;
				for (int i = 0; i < inv.getSizeInventory(); i++) {
					if (inv.getStackInSlot(i) != null) {
						ItemStack j = inv.getStackInSlot(i);
						if (j.getItem() != null) {
							if (random.nextBoolean()) {
								inv.setInventorySlotContents(i, null);
							}
						}
					}
				}

			} else if (number == 3) {

				// spawning monsters
				for (int i = 0; i < +random.nextInt(2); i++) {
					Entity entity = new EntityCreeper(worldTo);
					entity.setPosition(player.posX + random.nextInt(6) - 3, player.posY, player.posZ + random.nextInt(6) - 3);
					worldTo.spawnEntityInWorld(entity);
				}
				for (int i = 0; i < 3 + random.nextInt(2); i++) {
					Entity entity = new EntityZombie(worldTo);
					entity.setPosition(player.posX + random.nextInt(6) - 3, player.posY, player.posZ + random.nextInt(6) - 3);

					worldTo.spawnEntityInWorld(entity);
				}
				for (int i = 0; i < 2 + random.nextInt(2); i++) {
					Entity entity = new EntityPigZombie(worldTo);
					entity.setPosition(player.posX + random.nextInt(6) - 3, player.posY, player.posZ + random.nextInt(6) - 3);
					worldTo.spawnEntityInWorld(entity);
				}

			}

			else if (number == 4) {
				player.experience = 0;
				player.experienceLevel = player.experienceLevel - random.nextInt(((player.experienceLevel - 1) * 3) / 4) - 1;
				player.setFire(20);

			}

			else if (number == 5 || number == 6) {
				// Confusion + Blindness + Hunger + Damage

				player.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 400 - armorBoost * 5));
				player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 400 - armorBoost * 5));
				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 350 - armorBoost * 6));
				player.addPotionEffect(new PotionEffect(Potion.hunger.getId(), 350 - armorBoost * 5));
				player.addPotionEffect(new PotionEffect(Potion.harm.getId(), 12));

			} else if (number == 7) {

			}

		}

		tag.setInteger("UsedTeleporter", 0);

	}

	@SubscribeEvent
	public void ItemCrafted(ItemCraftedEvent event) {

		if (event.crafting.getItem() instanceof ItemFood) {
			ItemStack craftedItem = event.crafting;

			List<ItemStack> potions = new ArrayList<ItemStack>();
			ItemStack usedFood = new ItemStack(Items.bread);

			for (int slot = 0; slot < event.craftMatrix.getSizeInventory(); slot++) {
				if (event.craftMatrix.getStackInSlot(slot) != null) {
					if ((event.craftMatrix.getStackInSlot(slot).getItem()) instanceof ItemPotion) {
						potions.add(event.craftMatrix.getStackInSlot(slot));
						event.craftMatrix.setInventorySlotContents(slot, new ItemStack(Items.glass_bottle));
					} else if (event.craftMatrix.getStackInSlot(slot).isItemEqual(craftedItem)) {
						usedFood = event.craftMatrix.getStackInSlot(slot);
					}
				}
			}

			List<PotionEffect> effects = (Items.potionitem.getEffects(potions.get(0).getItemDamage()));

			for (int i = 1; i < potions.size(); i++) {
				effects.addAll((Items.potionitem.getEffects(potions.get(i).getItemDamage())));
			}

			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setBoolean("dimensionshift.isEffectApplied", true);


			if (effects != null && effects.size() > 0) {

				int[] potionIds = new int[effects.size()];
				int[] potionDuration = new int[effects.size()];

				for (int i = 0; i < effects.size(); i++) {
					System.out.println("adding ids and duration");
					potionIds[i] = effects.get(i).getPotionID();
					potionDuration[i] = effects.get(i).getDuration();
				}

				if (usedFood.hasTagCompound() && usedFood.getTagCompound().getBoolean("dimensionshift.isEffectApplied")) {
					nbt.setIntArray("dimensionshift.effectType", ArrayUtils.addAll(usedFood.getTagCompound().getIntArray("dimensionshift.effectType"), potionIds));
					nbt.setIntArray("dimensionshift.effectDuration", ArrayUtils.addAll(usedFood.getTagCompound().getIntArray("dimensionshift.effectDuration"), potionDuration));
				} else {
					nbt.setIntArray("dimensionshift.effectType", potionIds);
					nbt.setIntArray("dimensionshift.effectDuration", potionDuration);
				}
				craftedItem.setTagCompound(nbt);

			}
		}

	}

}
