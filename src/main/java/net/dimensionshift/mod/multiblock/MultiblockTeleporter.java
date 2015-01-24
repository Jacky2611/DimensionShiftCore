package net.dimensionshift.mod.multiblock;

import net.dimensionshift.mod.DimensionShift;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class MultiblockTeleporter {

	public static boolean isMultiBlockStructure(World world, int x, int y, int z) {

		if (checkNorth(world, x, y, z)) /* North */
		return true;
		if (checkEast(world, x, y, z)) /* East */
		return true;
		if (checkSouth(world, x, y, z)) /* South */
		return true;
		if (checkWest(world, x, y, z)) /* West */
		return true;
		return false;
	}

	private static boolean checkNorth(World world, int x, int y, int z) {
		if (world.getBlock(x + -2, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
			if (world.getBlock(x + -2, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
				if (world.getBlock(x + -2, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
					if (world.getBlock(x + -2, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
						if (world.getBlock(x + -2, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
							if (world.getBlock(x + -2, y + 1, z + 2) == DimensionShift.blockMachineBlock) {
								if (world.getBlock(x + -2, y + 1, z + 1) == Blocks.iron_bars) {
									if (world.getBlock(x + -2, y + 1, z + 0) == Blocks.iron_bars) {
										if (world.getBlock(x + -2, y + 1, z + -1) == Blocks.iron_bars) {
											if (world.getBlock(x + -2, y + 1, z + -2) == DimensionShift.blockMachineBlock) {
												if (world.getBlock(x + -2, y + 2, z + 2) == DimensionShift.blockMachineBlock) {
													if (world.getBlock(x + -2, y + 2, z + 1) == Blocks.iron_bars) {
														if (world.getBlock(x + -2, y + 2, z + 0) == Blocks.iron_bars) {
															if (world.getBlock(x + -2, y + 2, z + -1) == Blocks.iron_bars) {
																if (world.getBlock(x + -2, y + 2, z + -2) == DimensionShift.blockMachineBlock) {
																	if (world.getBlock(x + -2, y + 3, z + 2) == DimensionShift.blockMachineBlock) {
																		if (world.getBlock(x + -2, y + 3, z + 1) == Blocks.iron_bars) {
																			if (world.getBlock(x + -2, y + 3, z + 0) == Blocks.iron_bars) {
																				if (world.getBlock(x + -2, y + 3, z + -1) == Blocks.iron_bars) {
																					if (world.getBlock(x + -2, y + 3, z + -2) == DimensionShift.blockMachineBlock) {
																						if (world.getBlock(x + -2, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																							if (world.getBlock(x + -2, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																								if (world.getBlock(x + -2, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																									if (world.getBlock(x + -2, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																										if (world.getBlock(x + -2, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																											if (world.getBlock(x + -1, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																												if (world.getBlock(x + -1, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																													if (world.getBlock(x + -1, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																														if (world.getBlock(x + -1, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																															if (world.getBlock(x + -1, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																if (world.getBlock(x + -1, y + 1, z + 2) == Blocks.iron_bars) {
																																	if (world.getBlock(x + -1, y + 1, z + 1) == Blocks.air) {
																																		if (world.getBlock(x + -1, y + 1, z + 0) == Blocks.air) {
																																			if (world.getBlock(x + -1, y + 1, z + -1) == Blocks.air) {
																																				if (world.getBlock(x + -1, y + 1, z + -2) == Blocks.iron_bars) {
																																					if (world.getBlock(x + -1, y + 2, z + 2) == Blocks.iron_bars) {
																																						if (world.getBlock(x + -1, y + 2, z + 1) == Blocks.air) {
																																							if (world.getBlock(x + -1, y + 2, z + 0) == Blocks.air) {
																																								if (world.getBlock(x + -1, y + 2, z + -1) == Blocks.air) {
																																									if (world.getBlock(x + -1, y + 2, z + -2) == Blocks.iron_bars) {
																																										if (world.getBlock(x + -1, y + 3, z + 2) == Blocks.iron_bars) {
																																											if (world.getBlock(x + -1, y + 3, z + 1) == Blocks.air) {
																																												if (world.getBlock(x + -1, y + 3, z + 0) == Blocks.air) {
																																													if (world.getBlock(x + -1, y + 3, z + -1) == Blocks.air) {
																																														if (world.getBlock(x + -1, y + 3, z + -2) == Blocks.iron_bars) {
																																															if (world.getBlock(x + -1, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																if (world.getBlock(x + -1, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																	if (world.getBlock(x + -1, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																		if (world.getBlock(x + -1, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																			if (world.getBlock(x + -1, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																				if (world.getBlock(x + 0, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																																					if (world.getBlock(x + 0, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																																						if (world.getBlock(x + 0, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																																							if (world.getBlock(x + 0, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																																								if (world.getBlock(x + 0, y + 1, z + 2) == Blocks.air) {
																																																									if (world.getBlock(x + 0, y + 1, z + 1) == Blocks.air) {
																																																										if (world.getBlock(x + 0, y + 1, z + 0) == Blocks.air) {
																																																											if (world.getBlock(x + 0, y + 1, z + -1) == Blocks.air) {
																																																												if (world.getBlock(x + 0, y + 1, z + -2) == Blocks.iron_bars) {
																																																													if (world.getBlock(x + 0, y + 2, z + 2) == Blocks.air) {
																																																														if (world.getBlock(x + 0, y + 2, z + 1) == Blocks.air) {
																																																															if (world.getBlock(x + 0, y + 2, z + 0) == Blocks.air) {
																																																																if (world.getBlock(x + 0, y + 2, z + -1) == Blocks.air) {
																																																																	if (world.getBlock(x + 0, y + 2, z + -2) == Blocks.iron_bars) {
																																																																		if (world.getBlock(x + 0, y + 3, z + 2) == Blocks.iron_bars) {
																																																																			if (world.getBlock(x + 0, y + 3, z + 1) == Blocks.air) {
																																																																				if (world.getBlock(x + 0, y + 3, z + 0) == Blocks.air) {
																																																																					if (world.getBlock(x + 0, y + 3, z + -1) == Blocks.air) {
																																																																						if (world.getBlock(x + 0, y + 3, z + -2) == Blocks.iron_bars) {
																																																																							if (world.getBlock(x + 0, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																																								if (world.getBlock(x + 0, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																																									if (world.getBlock(x + 0, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																										if (world.getBlock(x + 0, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																																											if (world.getBlock(x + 0, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																																												if (world.getBlock(x + 1, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																																																													if (world.getBlock(x + 1, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																																																														if (world.getBlock(x + 1, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																																															if (world.getBlock(x + 1, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																if (world.getBlock(x + 1, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																	if (world.getBlock(x + 1, y + 1, z + 2) == Blocks.iron_bars) {
																																																																																		if (world.getBlock(x + 1, y + 1, z + 1) == Blocks.air) {
																																																																																			if (world.getBlock(x + 1, y + 1, z + 0) == Blocks.air) {
																																																																																				if (world.getBlock(x + 1, y + 1, z + -1) == Blocks.air) {
																																																																																					if (world.getBlock(x + 1, y + 1, z + -2) == Blocks.iron_bars) {
																																																																																						if (world.getBlock(x + 1, y + 2, z + 2) == Blocks.iron_bars) {
																																																																																							if (world.getBlock(x + 1, y + 2, z + 1) == Blocks.air) {
																																																																																								if (world.getBlock(x + 1, y + 2, z + 0) == Blocks.air) {
																																																																																									if (world.getBlock(x + 1, y + 2, z + -1) == Blocks.air) {
																																																																																										if (world.getBlock(x + 1, y + 2, z + -2) == Blocks.iron_bars) {
																																																																																											if (world.getBlock(x + 1, y + 3, z + 2) == Blocks.iron_bars) {
																																																																																												if (world.getBlock(x + 1, y + 3, z + 1) == Blocks.air) {
																																																																																													if (world.getBlock(x + 1, y + 3, z + 0) == Blocks.air) {
																																																																																														if (world.getBlock(x + 1, y + 3, z + -1) == Blocks.air) {
																																																																																															if (world.getBlock(x + 1, y + 3, z + -2) == Blocks.iron_bars) {
																																																																																																if (world.getBlock(x + 1, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																	if (world.getBlock(x + 1, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																																		if (world.getBlock(x + 1, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																																																			if (world.getBlock(x + 1, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																																				if (world.getBlock(x + 1, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																					if (world.getBlock(x + 2, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																						if (world.getBlock(x + 2, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																																							if (world.getBlock(x + 2, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																																																																								if (world.getBlock(x + 2, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																																									if (world.getBlock(x + 2, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																										if (world.getBlock(x + 2, y + 1, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																											if (world.getBlock(x + 2, y + 1, z + 1) == Blocks.iron_bars) {
																																																																																																												if (world.getBlock(x + 2, y + 1, z + 0) == Blocks.iron_bars) {
																																																																																																													if (world.getBlock(x + 2, y + 1, z + -1) == Blocks.iron_bars) {
																																																																																																														if (world.getBlock(x + 2, y + 1, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																															if (world.getBlock(x + 2, y + 2, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																if (world.getBlock(x + 2, y + 2, z + 1) == Blocks.iron_bars) {
																																																																																																																	if (world.getBlock(x + 2, y + 2, z + 0) == Blocks.iron_bars) {
																																																																																																																		if (world.getBlock(x + 2, y + 2, z + -1) == Blocks.iron_bars) {
																																																																																																																			if (world.getBlock(x + 2, y + 2, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																				if (world.getBlock(x + 2, y + 3, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																					if (world.getBlock(x + 2, y + 3, z + 1) == Blocks.iron_bars) {
																																																																																																																						if (world.getBlock(x + 2, y + 3, z + 0) == Blocks.iron_bars) {
																																																																																																																							if (world.getBlock(x + 2, y + 3, z + -1) == Blocks.iron_bars) {
																																																																																																																								if (world.getBlock(x + 2, y + 3, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																									if (world.getBlock(x + 2, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																										if (world.getBlock(x + 2, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																																																											if (world.getBlock(x + 2, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																																																																												if (world.getBlock(x + 2, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																																																													if (world.getBlock(x + 2, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																														return true;
																																																																																																																													}
																																																																																																																												}
																																																																																																																											}
																																																																																																																										}
																																																																																																																									}
																																																																																																																								}
																																																																																																																							}
																																																																																																																						}
																																																																																																																					}
																																																																																																																				}
																																																																																																																			}
																																																																																																																		}
																																																																																																																	}
																																																																																																																}
																																																																																																															}
																																																																																																														}
																																																																																																													}
																																																																																																												}
																																																																																																											}
																																																																																																										}
																																																																																																									}
																																																																																																								}
																																																																																																							}
																																																																																																						}
																																																																																																					}
																																																																																																				}
																																																																																																			}
																																																																																																		}
																																																																																																	}
																																																																																																}
																																																																																															}
																																																																																														}
																																																																																													}
																																																																																												}
																																																																																											}
																																																																																										}
																																																																																									}
																																																																																								}
																																																																																							}
																																																																																						}
																																																																																					}
																																																																																				}
																																																																																			}
																																																																																		}
																																																																																	}
																																																																																}
																																																																															}
																																																																														}
																																																																													}
																																																																												}
																																																																											}
																																																																										}
																																																																									}
																																																																								}
																																																																							}
																																																																						}
																																																																					}
																																																																				}
																																																																			}
																																																																		}
																																																																	}
																																																																}
																																																															}
																																																														}
																																																													}
																																																												}
																																																											}
																																																										}
																																																									}
																																																								}
																																																							}
																																																						}
																																																					}
																																																				}
																																																			}
																																																		}
																																																	}
																																																}
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private static boolean checkEast(World world, int x, int y, int z) {
		if (world.getBlock(x + -2, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
			if (world.getBlock(x + -1, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
				if (world.getBlock(x + 0, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
					if (world.getBlock(x + 1, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
						if (world.getBlock(x + 2, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
							if (world.getBlock(x + -2, y + 1, z + -2) == DimensionShift.blockMachineBlock) {
								if (world.getBlock(x + -1, y + 1, z + -2) == Blocks.iron_bars) {
									if (world.getBlock(x + 0, y + 1, z + -2) == Blocks.iron_bars) {
										if (world.getBlock(x + 1, y + 1, z + -2) == Blocks.iron_bars) {
											if (world.getBlock(x + 2, y + 1, z + -2) == DimensionShift.blockMachineBlock) {
												if (world.getBlock(x + -2, y + 2, z + -2) == DimensionShift.blockMachineBlock) {
													if (world.getBlock(x + -1, y + 2, z + -2) == Blocks.iron_bars) {
														if (world.getBlock(x + 0, y + 2, z + -2) == Blocks.iron_bars) {
															if (world.getBlock(x + 1, y + 2, z + -2) == Blocks.iron_bars) {
																if (world.getBlock(x + 2, y + 2, z + -2) == DimensionShift.blockMachineBlock) {
																	if (world.getBlock(x + -2, y + 3, z + -2) == DimensionShift.blockMachineBlock) {
																		if (world.getBlock(x + -1, y + 3, z + -2) == Blocks.iron_bars) {
																			if (world.getBlock(x + 0, y + 3, z + -2) == Blocks.iron_bars) {
																				if (world.getBlock(x + 1, y + 3, z + -2) == Blocks.iron_bars) {
																					if (world.getBlock(x + 2, y + 3, z + -2) == DimensionShift.blockMachineBlock) {
																						if (world.getBlock(x + -2, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																							if (world.getBlock(x + -1, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																								if (world.getBlock(x + 0, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																									if (world.getBlock(x + 1, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																										if (world.getBlock(x + 2, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																											if (world.getBlock(x + -2, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																												if (world.getBlock(x + -1, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																													if (world.getBlock(x + 0, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																														if (world.getBlock(x + 1, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																															if (world.getBlock(x + 2, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																if (world.getBlock(x + -2, y + 1, z + -1) == Blocks.iron_bars) {
																																	if (world.getBlock(x + -1, y + 1, z + -1) == Blocks.air) {
																																		if (world.getBlock(x + 0, y + 1, z + -1) == Blocks.air) {
																																			if (world.getBlock(x + 1, y + 1, z + -1) == Blocks.air) {
																																				if (world.getBlock(x + 2, y + 1, z + -1) == Blocks.iron_bars) {
																																					if (world.getBlock(x + -2, y + 2, z + -1) == Blocks.iron_bars) {
																																						if (world.getBlock(x + -1, y + 2, z + -1) == Blocks.air) {
																																							if (world.getBlock(x + 0, y + 2, z + -1) == Blocks.air) {
																																								if (world.getBlock(x + 1, y + 2, z + -1) == Blocks.air) {
																																									if (world.getBlock(x + 2, y + 2, z + -1) == Blocks.iron_bars) {
																																										if (world.getBlock(x + -2, y + 3, z + -1) == Blocks.iron_bars) {
																																											if (world.getBlock(x + -1, y + 3, z + -1) == Blocks.air) {
																																												if (world.getBlock(x + 0, y + 3, z + -1) == Blocks.air) {
																																													if (world.getBlock(x + 1, y + 3, z + -1) == Blocks.air) {
																																														if (world.getBlock(x + 2, y + 3, z + -1) == Blocks.iron_bars) {
																																															if (world.getBlock(x + -2, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																if (world.getBlock(x + -1, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																	if (world.getBlock(x + 0, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																		if (world.getBlock(x + 1, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																			if (world.getBlock(x + 2, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																				if (world.getBlock(x + -2, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																					if (world.getBlock(x + -1, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																						if (world.getBlock(x + 1, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																							if (world.getBlock(x + 2, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																								if (world.getBlock(x + -2, y + 1, z + 0) == Blocks.air) {
																																																									if (world.getBlock(x + -1, y + 1, z + 0) == Blocks.air) {
																																																										if (world.getBlock(x + 0, y + 1, z + 0) == Blocks.air) {
																																																											if (world.getBlock(x + 1, y + 1, z + 0) == Blocks.air) {
																																																												if (world.getBlock(x + 2, y + 1, z + 0) == Blocks.iron_bars) {
																																																													if (world.getBlock(x + -2, y + 2, z + 0) == Blocks.air) {
																																																														if (world.getBlock(x + -1, y + 2, z + 0) == Blocks.air) {
																																																															if (world.getBlock(x + 0, y + 2, z + 0) == Blocks.air) {
																																																																if (world.getBlock(x + 1, y + 2, z + 0) == Blocks.air) {
																																																																	if (world.getBlock(x + 2, y + 2, z + 0) == Blocks.iron_bars) {
																																																																		if (world.getBlock(x + -2, y + 3, z + 0) == Blocks.iron_bars) {
																																																																			if (world.getBlock(x + -1, y + 3, z + 0) == Blocks.air) {
																																																																				if (world.getBlock(x + 0, y + 3, z + 0) == Blocks.air) {
																																																																					if (world.getBlock(x + 1, y + 3, z + 0) == Blocks.air) {
																																																																						if (world.getBlock(x + 2, y + 3, z + 0) == Blocks.iron_bars) {
																																																																							if (world.getBlock(x + -2, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																								if (world.getBlock(x + -1, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																									if (world.getBlock(x + 0, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																										if (world.getBlock(x + 1, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																											if (world.getBlock(x + 2, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																												if (world.getBlock(x + -2, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																																																													if (world.getBlock(x + -1, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																																																														if (world.getBlock(x + 0, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																																																															if (world.getBlock(x + 1, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																if (world.getBlock(x + 2, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																	if (world.getBlock(x + -2, y + 1, z + 1) == Blocks.iron_bars) {
																																																																																		if (world.getBlock(x + -1, y + 1, z + 1) == Blocks.air) {
																																																																																			if (world.getBlock(x + 0, y + 1, z + 1) == Blocks.air) {
																																																																																				if (world.getBlock(x + 1, y + 1, z + 1) == Blocks.air) {
																																																																																					if (world.getBlock(x + 2, y + 1, z + 1) == Blocks.iron_bars) {
																																																																																						if (world.getBlock(x + -2, y + 2, z + 1) == Blocks.iron_bars) {
																																																																																							if (world.getBlock(x + -1, y + 2, z + 1) == Blocks.air) {
																																																																																								if (world.getBlock(x + 0, y + 2, z + 1) == Blocks.air) {
																																																																																									if (world.getBlock(x + 1, y + 2, z + 1) == Blocks.air) {
																																																																																										if (world.getBlock(x + 2, y + 2, z + 1) == Blocks.iron_bars) {
																																																																																											if (world.getBlock(x + -2, y + 3, z + 1) == Blocks.iron_bars) {
																																																																																												if (world.getBlock(x + -1, y + 3, z + 1) == Blocks.air) {
																																																																																													if (world.getBlock(x + 0, y + 3, z + 1) == Blocks.air) {
																																																																																														if (world.getBlock(x + 1, y + 3, z + 1) == Blocks.air) {
																																																																																															if (world.getBlock(x + 2, y + 3, z + 1) == Blocks.iron_bars) {
																																																																																																if (world.getBlock(x + -2, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																																	if (world.getBlock(x + -1, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																																		if (world.getBlock(x + 0, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																																			if (world.getBlock(x + 1, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																																				if (world.getBlock(x + 2, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																																					if (world.getBlock(x + -2, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																						if (world.getBlock(x + -1, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																							if (world.getBlock(x + 0, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																								if (world.getBlock(x + 1, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																									if (world.getBlock(x + 2, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																										if (world.getBlock(x + -2, y + 1, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																											if (world.getBlock(x + -1, y + 1, z + 2) == Blocks.iron_bars) {
																																																																																																												if (world.getBlock(x + 0, y + 1, z + 2) == Blocks.iron_bars) {
																																																																																																													if (world.getBlock(x + 1, y + 1, z + 2) == Blocks.iron_bars) {
																																																																																																														if (world.getBlock(x + 2, y + 1, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																															if (world.getBlock(x + -2, y + 2, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																if (world.getBlock(x + -1, y + 2, z + 2) == Blocks.iron_bars) {
																																																																																																																	if (world.getBlock(x + 0, y + 2, z + 2) == Blocks.iron_bars) {
																																																																																																																		if (world.getBlock(x + 1, y + 2, z + 2) == Blocks.iron_bars) {
																																																																																																																			if (world.getBlock(x + 2, y + 2, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																				if (world.getBlock(x + -2, y + 3, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																					if (world.getBlock(x + -1, y + 3, z + 2) == Blocks.iron_bars) {
																																																																																																																						if (world.getBlock(x + 0, y + 3, z + 2) == Blocks.iron_bars) {
																																																																																																																							if (world.getBlock(x + 1, y + 3, z + 2) == Blocks.iron_bars) {
																																																																																																																								if (world.getBlock(x + 2, y + 3, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																									if (world.getBlock(x + -2, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																										if (world.getBlock(x + -1, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																											if (world.getBlock(x + 0, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																												if (world.getBlock(x + 1, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																													if (world.getBlock(x + 2, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																														return true;
																																																																																																																													}
																																																																																																																												}
																																																																																																																											}
																																																																																																																										}
																																																																																																																									}
																																																																																																																								}
																																																																																																																							}
																																																																																																																						}
																																																																																																																					}
																																																																																																																				}
																																																																																																																			}
																																																																																																																		}
																																																																																																																	}
																																																																																																																}
																																																																																																															}
																																																																																																														}
																																																																																																													}
																																																																																																												}
																																																																																																											}
																																																																																																										}
																																																																																																									}
																																																																																																								}
																																																																																																							}
																																																																																																						}
																																																																																																					}
																																																																																																				}
																																																																																																			}
																																																																																																		}
																																																																																																	}
																																																																																																}
																																																																																															}
																																																																																														}
																																																																																													}
																																																																																												}
																																																																																											}
																																																																																										}
																																																																																									}
																																																																																								}
																																																																																							}
																																																																																						}
																																																																																					}
																																																																																				}
																																																																																			}
																																																																																		}
																																																																																	}
																																																																																}
																																																																															}
																																																																														}
																																																																													}
																																																																												}
																																																																											}
																																																																										}
																																																																									}
																																																																								}
																																																																							}
																																																																						}
																																																																					}
																																																																				}
																																																																			}
																																																																		}
																																																																	}
																																																																}
																																																															}
																																																														}
																																																													}
																																																												}
																																																											}
																																																										}
																																																									}
																																																								}
																																																							}
																																																						}
																																																					}
																																																				}
																																																			}
																																																		}
																																																	}
																																																}
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private static boolean checkSouth(World world, int x, int y, int z) {
		if (world.getBlock(x + 2, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
			if (world.getBlock(x + 2, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
				if (world.getBlock(x + 2, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
					if (world.getBlock(x + 2, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
						if (world.getBlock(x + 2, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
							if (world.getBlock(x + 2, y + 1, z + -2) == DimensionShift.blockMachineBlock) {
								if (world.getBlock(x + 2, y + 1, z + -1) == Blocks.iron_bars) {
									if (world.getBlock(x + 2, y + 1, z + 0) == Blocks.iron_bars) {
										if (world.getBlock(x + 2, y + 1, z + 1) == Blocks.iron_bars) {
											if (world.getBlock(x + 2, y + 1, z + 2) == DimensionShift.blockMachineBlock) {
												if (world.getBlock(x + 2, y + 2, z + -2) == DimensionShift.blockMachineBlock) {
													if (world.getBlock(x + 2, y + 2, z + -1) == Blocks.iron_bars) {
														if (world.getBlock(x + 2, y + 2, z + 0) == Blocks.iron_bars) {
															if (world.getBlock(x + 2, y + 2, z + 1) == Blocks.iron_bars) {
																if (world.getBlock(x + 2, y + 2, z + 2) == DimensionShift.blockMachineBlock) {
																	if (world.getBlock(x + 2, y + 3, z + -2) == DimensionShift.blockMachineBlock) {
																		if (world.getBlock(x + 2, y + 3, z + -1) == Blocks.iron_bars) {
																			if (world.getBlock(x + 2, y + 3, z + 0) == Blocks.iron_bars) {
																				if (world.getBlock(x + 2, y + 3, z + 1) == Blocks.iron_bars) {
																					if (world.getBlock(x + 2, y + 3, z + 2) == DimensionShift.blockMachineBlock) {
																						if (world.getBlock(x + 2, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																							if (world.getBlock(x + 2, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																								if (world.getBlock(x + 2, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																									if (world.getBlock(x + 2, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																										if (world.getBlock(x + 2, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																											if (world.getBlock(x + 1, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																												if (world.getBlock(x + 1, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																													if (world.getBlock(x + 1, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																														if (world.getBlock(x + 1, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																															if (world.getBlock(x + 1, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																if (world.getBlock(x + 1, y + 1, z + -2) == Blocks.iron_bars) {
																																	if (world.getBlock(x + 1, y + 1, z + -1) == Blocks.air) {
																																		if (world.getBlock(x + 1, y + 1, z + 0) == Blocks.air) {
																																			if (world.getBlock(x + 1, y + 1, z + 1) == Blocks.air) {
																																				if (world.getBlock(x + 1, y + 1, z + 2) == Blocks.iron_bars) {
																																					if (world.getBlock(x + 1, y + 2, z + -2) == Blocks.iron_bars) {
																																						if (world.getBlock(x + 1, y + 2, z + -1) == Blocks.air) {
																																							if (world.getBlock(x + 1, y + 2, z + 0) == Blocks.air) {
																																								if (world.getBlock(x + 1, y + 2, z + 1) == Blocks.air) {
																																									if (world.getBlock(x + 1, y + 2, z + 2) == Blocks.iron_bars) {
																																										if (world.getBlock(x + 1, y + 3, z + -2) == Blocks.iron_bars) {
																																											if (world.getBlock(x + 1, y + 3, z + -1) == Blocks.air) {
																																												if (world.getBlock(x + 1, y + 3, z + 0) == Blocks.air) {
																																													if (world.getBlock(x + 1, y + 3, z + 1) == Blocks.air) {
																																														if (world.getBlock(x + 1, y + 3, z + 2) == Blocks.iron_bars) {
																																															if (world.getBlock(x + 1, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																if (world.getBlock(x + 1, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																	if (world.getBlock(x + 1, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																		if (world.getBlock(x + 1, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																			if (world.getBlock(x + 1, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																				if (world.getBlock(x + 0, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																																					if (world.getBlock(x + 0, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																																						if (world.getBlock(x + 0, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																																							if (world.getBlock(x + 0, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																																								if (world.getBlock(x + 0, y + 1, z + -2) == Blocks.air) {
																																																									if (world.getBlock(x + 0, y + 1, z + -1) == Blocks.air) {
																																																										if (world.getBlock(x + 0, y + 1, z + 0) == Blocks.air) {
																																																											if (world.getBlock(x + 0, y + 1, z + 1) == Blocks.air) {
																																																												if (world.getBlock(x + 0, y + 1, z + 2) == Blocks.iron_bars) {
																																																													if (world.getBlock(x + 0, y + 2, z + -2) == Blocks.air) {
																																																														if (world.getBlock(x + 0, y + 2, z + -1) == Blocks.air) {
																																																															if (world.getBlock(x + 0, y + 2, z + 0) == Blocks.air) {
																																																																if (world.getBlock(x + 0, y + 2, z + 1) == Blocks.air) {
																																																																	if (world.getBlock(x + 0, y + 2, z + 2) == Blocks.iron_bars) {
																																																																		if (world.getBlock(x + 0, y + 3, z + -2) == Blocks.iron_bars) {
																																																																			if (world.getBlock(x + 0, y + 3, z + -1) == Blocks.air) {
																																																																				if (world.getBlock(x + 0, y + 3, z + 0) == Blocks.air) {
																																																																					if (world.getBlock(x + 0, y + 3, z + 1) == Blocks.air) {
																																																																						if (world.getBlock(x + 0, y + 3, z + 2) == Blocks.iron_bars) {
																																																																							if (world.getBlock(x + 0, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																																								if (world.getBlock(x + 0, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																																									if (world.getBlock(x + 0, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																										if (world.getBlock(x + 0, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																																											if (world.getBlock(x + 0, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																																												if (world.getBlock(x + -1, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																																																													if (world.getBlock(x + -1, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																																																														if (world.getBlock(x + -1, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																																															if (world.getBlock(x + -1, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																if (world.getBlock(x + -1, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																	if (world.getBlock(x + -1, y + 1, z + -2) == Blocks.iron_bars) {
																																																																																		if (world.getBlock(x + -1, y + 1, z + -1) == Blocks.air) {
																																																																																			if (world.getBlock(x + -1, y + 1, z + 0) == Blocks.air) {
																																																																																				if (world.getBlock(x + -1, y + 1, z + 1) == Blocks.air) {
																																																																																					if (world.getBlock(x + -1, y + 1, z + 2) == Blocks.iron_bars) {
																																																																																						if (world.getBlock(x + -1, y + 2, z + -2) == Blocks.iron_bars) {
																																																																																							if (world.getBlock(x + -1, y + 2, z + -1) == Blocks.air) {
																																																																																								if (world.getBlock(x + -1, y + 2, z + 0) == Blocks.air) {
																																																																																									if (world.getBlock(x + -1, y + 2, z + 1) == Blocks.air) {
																																																																																										if (world.getBlock(x + -1, y + 2, z + 2) == Blocks.iron_bars) {
																																																																																											if (world.getBlock(x + -1, y + 3, z + -2) == Blocks.iron_bars) {
																																																																																												if (world.getBlock(x + -1, y + 3, z + -1) == Blocks.air) {
																																																																																													if (world.getBlock(x + -1, y + 3, z + 0) == Blocks.air) {
																																																																																														if (world.getBlock(x + -1, y + 3, z + 1) == Blocks.air) {
																																																																																															if (world.getBlock(x + -1, y + 3, z + 2) == Blocks.iron_bars) {
																																																																																																if (world.getBlock(x + -1, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																	if (world.getBlock(x + -1, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																																		if (world.getBlock(x + -1, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																																																			if (world.getBlock(x + -1, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																																				if (world.getBlock(x + -1, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																					if (world.getBlock(x + -2, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																						if (world.getBlock(x + -2, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																																							if (world.getBlock(x + -2, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																																																																								if (world.getBlock(x + -2, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																																									if (world.getBlock(x + -2, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																										if (world.getBlock(x + -2, y + 1, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																											if (world.getBlock(x + -2, y + 1, z + -1) == Blocks.iron_bars) {
																																																																																																												if (world.getBlock(x + -2, y + 1, z + 0) == Blocks.iron_bars) {
																																																																																																													if (world.getBlock(x + -2, y + 1, z + 1) == Blocks.iron_bars) {
																																																																																																														if (world.getBlock(x + -2, y + 1, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																															if (world.getBlock(x + -2, y + 2, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																if (world.getBlock(x + -2, y + 2, z + -1) == Blocks.iron_bars) {
																																																																																																																	if (world.getBlock(x + -2, y + 2, z + 0) == Blocks.iron_bars) {
																																																																																																																		if (world.getBlock(x + -2, y + 2, z + 1) == Blocks.iron_bars) {
																																																																																																																			if (world.getBlock(x + -2, y + 2, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																				if (world.getBlock(x + -2, y + 3, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																					if (world.getBlock(x + -2, y + 3, z + -1) == Blocks.iron_bars) {
																																																																																																																						if (world.getBlock(x + -2, y + 3, z + 0) == Blocks.iron_bars) {
																																																																																																																							if (world.getBlock(x + -2, y + 3, z + 1) == Blocks.iron_bars) {
																																																																																																																								if (world.getBlock(x + -2, y + 3, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																									if (world.getBlock(x + -2, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																										if (world.getBlock(x + -2, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																																																											if (world.getBlock(x + -2, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																																																																												if (world.getBlock(x + -2, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																																																																																													if (world.getBlock(x + -2, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																																																																																																																														return true;
																																																																																																																													}
																																																																																																																												}
																																																																																																																											}
																																																																																																																										}
																																																																																																																									}
																																																																																																																								}
																																																																																																																							}
																																																																																																																						}
																																																																																																																					}
																																																																																																																				}
																																																																																																																			}
																																																																																																																		}
																																																																																																																	}
																																																																																																																}
																																																																																																															}
																																																																																																														}
																																																																																																													}
																																																																																																												}
																																																																																																											}
																																																																																																										}
																																																																																																									}
																																																																																																								}
																																																																																																							}
																																																																																																						}
																																																																																																					}
																																																																																																				}
																																																																																																			}
																																																																																																		}
																																																																																																	}
																																																																																																}
																																																																																															}
																																																																																														}
																																																																																													}
																																																																																												}
																																																																																											}
																																																																																										}
																																																																																									}
																																																																																								}
																																																																																							}
																																																																																						}
																																																																																					}
																																																																																				}
																																																																																			}
																																																																																		}
																																																																																	}
																																																																																}
																																																																															}
																																																																														}
																																																																													}
																																																																												}
																																																																											}
																																																																										}
																																																																									}
																																																																								}
																																																																							}
																																																																						}
																																																																					}
																																																																				}
																																																																			}
																																																																		}
																																																																	}
																																																																}
																																																															}
																																																														}
																																																													}
																																																												}
																																																											}
																																																										}
																																																									}
																																																								}
																																																							}
																																																						}
																																																					}
																																																				}
																																																			}
																																																		}
																																																	}
																																																}
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private static boolean checkWest(World world, int x, int y, int z) {
		if (world.getBlock(x + 2, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
			if (world.getBlock(x + 1, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
				if (world.getBlock(x + 0, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
					if (world.getBlock(x + -1, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
						if (world.getBlock(x + -2, y + 0, z + 2) == DimensionShift.blockMachineBlock) {
							if (world.getBlock(x + 2, y + 1, z + 2) == DimensionShift.blockMachineBlock) {
								if (world.getBlock(x + 1, y + 1, z + 2) == Blocks.iron_bars) {
									if (world.getBlock(x + 0, y + 1, z + 2) == Blocks.iron_bars) {
										if (world.getBlock(x + -1, y + 1, z + 2) == Blocks.iron_bars) {
											if (world.getBlock(x + -2, y + 1, z + 2) == DimensionShift.blockMachineBlock) {
												if (world.getBlock(x + 2, y + 2, z + 2) == DimensionShift.blockMachineBlock) {
													if (world.getBlock(x + 1, y + 2, z + 2) == Blocks.iron_bars) {
														if (world.getBlock(x + 0, y + 2, z + 2) == Blocks.iron_bars) {
															if (world.getBlock(x + -1, y + 2, z + 2) == Blocks.iron_bars) {
																if (world.getBlock(x + -2, y + 2, z + 2) == DimensionShift.blockMachineBlock) {
																	if (world.getBlock(x + 2, y + 3, z + 2) == DimensionShift.blockMachineBlock) {
																		if (world.getBlock(x + 1, y + 3, z + 2) == Blocks.iron_bars) {
																			if (world.getBlock(x + 0, y + 3, z + 2) == Blocks.iron_bars) {
																				if (world.getBlock(x + -1, y + 3, z + 2) == Blocks.iron_bars) {
																					if (world.getBlock(x + -2, y + 3, z + 2) == DimensionShift.blockMachineBlock) {
																						if (world.getBlock(x + 2, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																							if (world.getBlock(x + 1, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																								if (world.getBlock(x + 0, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																									if (world.getBlock(x + -1, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																										if (world.getBlock(x + -2, y + 4, z + 2) == DimensionShift.blockMachineBlock) {
																											if (world.getBlock(x + 2, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																												if (world.getBlock(x + 1, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																													if (world.getBlock(x + 0, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																														if (world.getBlock(x + -1, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																															if (world.getBlock(x + -2, y + 0, z + 1) == DimensionShift.blockMachineBlock) {
																																if (world.getBlock(x + 2, y + 1, z + 1) == Blocks.iron_bars) {
																																	if (world.getBlock(x + 1, y + 1, z + 1) == Blocks.air) {
																																		if (world.getBlock(x + 0, y + 1, z + 1) == Blocks.air) {
																																			if (world.getBlock(x + -1, y + 1, z + 1) == Blocks.air) {
																																				if (world.getBlock(x + -2, y + 1, z + 1) == Blocks.iron_bars) {
																																					if (world.getBlock(x + 2, y + 2, z + 1) == Blocks.iron_bars) {
																																						if (world.getBlock(x + 1, y + 2, z + 1) == Blocks.air) {
																																							if (world.getBlock(x + 0, y + 2, z + 1) == Blocks.air) {
																																								if (world.getBlock(x + -1, y + 2, z + 1) == Blocks.air) {
																																									if (world.getBlock(x + -2, y + 2, z + 1) == Blocks.iron_bars) {
																																										if (world.getBlock(x + 2, y + 3, z + 1) == Blocks.iron_bars) {
																																											if (world.getBlock(x + 1, y + 3, z + 1) == Blocks.air) {
																																												if (world.getBlock(x + 0, y + 3, z + 1) == Blocks.air) {
																																													if (world.getBlock(x + -1, y + 3, z + 1) == Blocks.air) {
																																														if (world.getBlock(x + -2, y + 3, z + 1) == Blocks.iron_bars) {
																																															if (world.getBlock(x + 2, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																if (world.getBlock(x + 1, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																	if (world.getBlock(x + 0, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																		if (world.getBlock(x + -1, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																			if (world.getBlock(x + -2, y + 4, z + 1) == DimensionShift.blockMachineBlock) {
																																																				if (world.getBlock(x + 2, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																					if (world.getBlock(x + 1, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																						if (world.getBlock(x + -1, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																							if (world.getBlock(x + -2, y + 0, z + 0) == DimensionShift.blockMachineBlock) {
																																																								if (world.getBlock(x + 2, y + 1, z + 0) == Blocks.air) {
																																																									if (world.getBlock(x + 1, y + 1, z + 0) == Blocks.air) {
																																																										if (world.getBlock(x + 0, y + 1, z + 0) == Blocks.air) {
																																																											if (world.getBlock(x + -1, y + 1, z + 0) == Blocks.air) {
																																																												if (world.getBlock(x + -2, y + 1, z + 0) == Blocks.iron_bars) {
																																																													if (world.getBlock(x + 2, y + 2, z + 0) == Blocks.air) {
																																																														if (world.getBlock(x + 1, y + 2, z + 0) == Blocks.air) {
																																																															if (world.getBlock(x + 0, y + 2, z + 0) == Blocks.air) {
																																																																if (world.getBlock(x + -1, y + 2, z + 0) == Blocks.air) {
																																																																	if (world.getBlock(x + -2, y + 2, z + 0) == Blocks.iron_bars) {
																																																																		if (world.getBlock(x + 2, y + 3, z + 0) == Blocks.iron_bars) {
																																																																			if (world.getBlock(x + 1, y + 3, z + 0) == Blocks.air) {
																																																																				if (world.getBlock(x + 0, y + 3, z + 0) == Blocks.air) {
																																																																					if (world.getBlock(x + -1, y + 3, z + 0) == Blocks.air) {
																																																																						if (world.getBlock(x + -2, y + 3, z + 0) == Blocks.iron_bars) {
																																																																							if (world.getBlock(x + 2, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																								if (world.getBlock(x + 1, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																									if (world.getBlock(x + 0, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																										if (world.getBlock(x + -1, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																											if (world.getBlock(x + -2, y + 4, z + 0) == DimensionShift.blockMachineBlock) {
																																																																												if (world.getBlock(x + 2, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																																																													if (world.getBlock(x + 1, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																																																														if (world.getBlock(x + 0, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																																																															if (world.getBlock(x + -1, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																if (world.getBlock(x + -2, y + 0, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																	if (world.getBlock(x + 2, y + 1, z + -1) == Blocks.iron_bars) {
																																																																																		if (world.getBlock(x + 1, y + 1, z + -1) == Blocks.air) {
																																																																																			if (world.getBlock(x + 0, y + 1, z + -1) == Blocks.air) {
																																																																																				if (world.getBlock(x + -1, y + 1, z + -1) == Blocks.air) {
																																																																																					if (world.getBlock(x + -2, y + 1, z + -1) == Blocks.iron_bars) {
																																																																																						if (world.getBlock(x + 2, y + 2, z + -1) == Blocks.iron_bars) {
																																																																																							if (world.getBlock(x + 1, y + 2, z + -1) == Blocks.air) {
																																																																																								if (world.getBlock(x + 0, y + 2, z + -1) == Blocks.air) {
																																																																																									if (world.getBlock(x + -1, y + 2, z + -1) == Blocks.air) {
																																																																																										if (world.getBlock(x + -2, y + 2, z + -1) == Blocks.iron_bars) {
																																																																																											if (world.getBlock(x + 2, y + 3, z + -1) == Blocks.iron_bars) {
																																																																																												if (world.getBlock(x + 1, y + 3, z + -1) == Blocks.air) {
																																																																																													if (world.getBlock(x + 0, y + 3, z + -1) == Blocks.air) {
																																																																																														if (world.getBlock(x + -1, y + 3, z + -1) == Blocks.air) {
																																																																																															if (world.getBlock(x + -2, y + 3, z + -1) == Blocks.iron_bars) {
																																																																																																if (world.getBlock(x + 2, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																																	if (world.getBlock(x + 1, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																																		if (world.getBlock(x + 0, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																																			if (world.getBlock(x + -1, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																																				if (world.getBlock(x + -2, y + 4, z + -1) == DimensionShift.blockMachineBlock) {
																																																																																																					if (world.getBlock(x + 2, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																						if (world.getBlock(x + 1, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																							if (world.getBlock(x + 0, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																								if (world.getBlock(x + -1, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																									if (world.getBlock(x + -2, y + 0, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																										if (world.getBlock(x + 2, y + 1, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																											if (world.getBlock(x + 1, y + 1, z + -2) == Blocks.iron_bars) {
																																																																																																												if (world.getBlock(x + 0, y + 1, z + -2) == Blocks.iron_bars) {
																																																																																																													if (world.getBlock(x + -1, y + 1, z + -2) == Blocks.iron_bars) {
																																																																																																														if (world.getBlock(x + -2, y + 1, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																															if (world.getBlock(x + 2, y + 2, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																if (world.getBlock(x + 1, y + 2, z + -2) == Blocks.iron_bars) {
																																																																																																																	if (world.getBlock(x + 0, y + 2, z + -2) == Blocks.iron_bars) {
																																																																																																																		if (world.getBlock(x + -1, y + 2, z + -2) == Blocks.iron_bars) {
																																																																																																																			if (world.getBlock(x + -2, y + 2, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																				if (world.getBlock(x + 2, y + 3, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																					if (world.getBlock(x + 1, y + 3, z + -2) == Blocks.iron_bars) {
																																																																																																																						if (world.getBlock(x + 0, y + 3, z + -2) == Blocks.iron_bars) {
																																																																																																																							if (world.getBlock(x + -1, y + 3, z + -2) == Blocks.iron_bars) {
																																																																																																																								if (world.getBlock(x + -2, y + 3, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																									if (world.getBlock(x + 2, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																										if (world.getBlock(x + 1, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																											if (world.getBlock(x + 0, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																												if (world.getBlock(x + -1, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																													if (world.getBlock(x + -2, y + 4, z + -2) == DimensionShift.blockMachineBlock) {
																																																																																																																														return true;
																																																																																																																													}
																																																																																																																												}
																																																																																																																											}
																																																																																																																										}
																																																																																																																									}
																																																																																																																								}
																																																																																																																							}
																																																																																																																						}
																																																																																																																					}
																																																																																																																				}
																																																																																																																			}
																																																																																																																		}
																																																																																																																	}
																																																																																																																}
																																																																																																															}
																																																																																																														}
																																																																																																													}
																																																																																																												}
																																																																																																											}
																																																																																																										}
																																																																																																									}
																																																																																																								}
																																																																																																							}
																																																																																																						}
																																																																																																					}
																																																																																																				}
																																																																																																			}
																																																																																																		}
																																																																																																	}
																																																																																																}
																																																																																															}
																																																																																														}
																																																																																													}
																																																																																												}
																																																																																											}
																																																																																										}
																																																																																									}
																																																																																								}
																																																																																							}
																																																																																						}
																																																																																					}
																																																																																				}
																																																																																			}
																																																																																		}
																																																																																	}
																																																																																}
																																																																															}
																																																																														}
																																																																													}
																																																																												}
																																																																											}
																																																																										}
																																																																									}
																																																																								}
																																																																							}
																																																																						}
																																																																					}
																																																																				}
																																																																			}
																																																																		}
																																																																	}
																																																																}
																																																															}
																																																														}
																																																													}
																																																												}
																																																											}
																																																										}
																																																									}
																																																								}
																																																							}
																																																						}
																																																					}
																																																				}
																																																			}
																																																		}
																																																	}
																																																}
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

}
