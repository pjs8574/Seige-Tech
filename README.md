Seige-Tech
==========

PLANNED FEATURES

The general idea is to have consistent Teirs of preogression. The Prefixes being thus:

Basic > Improved > Advanced > Elite


Tier 0 is to be considred Vanilla blocks, Basic is Tier 1 and so forth.

Equivilent Tiers should do damage to each other. SO a Tier 1 explsoive should damage Tier 1 concrete.  
Attacking a lesser Tier, with a greater Tier should amplify the damage. (Note this wont really work with vanilla blocks without making our own explosion class--
so for now they are soley bound by blast resistence. -- THIS MAKES OBSIDIAN A PROBLEM)


Perhaps a Tier based modifier should be put into play..
Ex 1: A Basic PandaNade does 5 dmg to Basic Shawcrete. It should do Less (2.5?) to Improved shawcrete. And None to Advacned or Elite. 
Ex 2: An Improved Panda Nade should do 5 damage to Improved Shawcrete, but 7.5 to Basic Shawcrete. and 2.5 to Advanced Shawcrete

Each tier difference should add or remove by half the base damage

SO a Tier 4 vs a Tier 1 is a difference of 3 tiers so a T4 grenade vs a T1 Concrete should have its base damage multipled by 2.5


-- Current Concrete Mechanic -- 

Each Concrete has a hash table of potential damaging entities
Each explsion entity has a Percentage Chance of degrading the concrete into a cobblestone block

Pros - Random, Performant because no use of block metadata to track HP

Cons - Potential for a long Run of Lucky rolls or Unlucky Rolls that may bypass the concrete Easily or Not at all

-- The Potential concrete Hitpoint mechanic --
Since metadata is limits to a Bittle --4 Bits (0 to 15 int) 











