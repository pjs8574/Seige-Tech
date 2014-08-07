Seige-Tech
==========



--Mod Themes--
The general idea is to have consistent Tiers of progression. The Prefixes being thus:

Basic > Improved > Advanced > Elite

Another Theme should be the use of lower tiered items to make upper tier resources.  Improved Shawcrete requires Basic Shawcrete + obsidian to make.  Advanced PandaNades require the Improved varient, which in turn requires the Basic
 varient.

-REQ-How many of the previous Tier item needed to make the next tier item should remain consistent. Right now it is 6. 6 basicshawcrete to make ImpShawcrete, 6 BasicNades to make Improved nades.  This may be dropped to 4 -- a chat about resource balence is needed to finalize.

-REQ-How many of the crafted Items Produced should remain consistent accross item type. 8 Tier crafting material per craft. 8 concrete per craft. 2 grenades per craft. 1 explosive block per craft. A chat about resource balence is needed to finalize.


--Mod features (planned items, done iems etc)--


----Blocks:
Shawcrete -- concrete block for defense. Has all 4 tiers (partially implemented)

Seitersonic Explosive -- TnT block that is similar to a breaching charge. Has a direcitonal explosion. Supposed to be more   powerful at breaking concrete.  Has all 4 Tiers (Partially Implemented)

Netherite -- Crafting Material for Tier 2 items. Made with Blaze Rod, Glowstone Dust, Nether Quartz and Diamond, and Obsidian

Enderite -- Crafting material for Tier 3 items. Made with Endstone, Ender Eyes, and Netherite.

Astralium -- Crafting Material for Tier 4 items. Made with 8 Enderite surrounding a Nether Star.

----Items:
PandaNade -- throwable explosive. Has all 4 tiers, Less powerful than Seitersonic explosive at concrete penetration. (Partially Implemented)

Hunter's Compass  -- Compass that vaugey points in the direction of the nearest player that is NOT the player holding it. Each Tier adds a feature - such as a white list, or a vague amount of blocks distence instead of just direction.  (Concept stage)

----Devices:

Proximity Detector -- emits redstone signal when a player is near.  Each tier adds features, such as adjustale range , white list, etc

Hunter's Detector -- emits a redstone signal only when a specificly named player is online. No aditional tiers

Siege Laser -- Multiblock top tier device. Fires a laser that ahnnilates everything in its path for 64 blocks. Takes a nether star to power it. Long charge up time.




-----Other notes

Tier 0 is to be considred Vanilla blocks, Basic is Tier 1 and so forth.

Equivilent Tiers should do damage to each other. SO a Tier 1 explsoive should damage Tier 1 concrete.  
Attacking a lesser Tier, with a greater Tier should amplify the damage. (Note this wont really work with vanilla blocks without making our own explosion class--so for now they are soley bound by blast resistence. -- THIS MAKES OBSIDIAN A PROBLEM)


Perhaps a Tier based modifier should be put into play..
Ex 1: A Basic PandaNade does 5 dmg to Basic Shawcrete. It should do Less (2.5?) to Improved shawcrete. And None to Advacned or Elite. 
Ex 2: An Improved Panda Nade should do 5 damage to Improved Shawcrete, but 7.5 to Basic Shawcrete. and 2.5 to Advanced Shawcrete

Each tier difference should add or remove by half the base damage

SO a Tier 4 vs a Tier 1 is a difference of 3 tiers so a T4 grenade vs a T1 Concrete should have its base damage multipled by 2.5


-- Current Concrete Mechanic -- 

Each Concrete has a hash table of potential damaging entities
Each explsion entity has a Percentage Chance of degrading the concrete into a cobblestone block

Pros - Random - gambling is fun, Performant because no use of block metadata to track HP

Cons - Potential for a long Run of Lucky rolls or Unlucky Rolls that may bypass the concrete Easily or Not at all

-- The Potential concrete Hitpoint mechanic --
Since metadata is limits to a Bittle --4 Bits (0 to 15 int)-- The hit point mechanic would potentially use the metadata as a percentage of overall damage.

The block's HP would be a calculated number.  SO, on a newly made block the meta of 15 is set -- the block is at full HP. 

When the block takes daamge from an explosive -- the Meta value is retrieved,  multiplied by Tier+1 (Potentialy), to give the code the Block's current HP value.  The Tier of the explosive used is then compared to tier of the concrete block,  to create a damage multiplier.  Too add some sense of randomness (so no one person can say I NEED exactly X type of explosives to break this), the base damage of the explosive is randomly rolled in a range, then multipled by the Tier modifier, the damage is then applied to teh Current HP of the block.  A check is made to see if the HP drops low enough to degrade int cobblestone -- or if so much damage is applied the block just vaporizes.  Vaporizing occurs if the damage takes twice its Make HP in damage. (Basica concrete's base HP is 30 (15*(tier1+1)))  If it takes 60 damage in one explosion it just turns into an air block.  

Pros : Neat effect, No more big lucky/unlucky streaks where the player is frustrated
Con: Potential Performannce issues, damage more predicatable










