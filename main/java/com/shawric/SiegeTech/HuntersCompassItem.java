
/*

ON use of the compass

get a list of all players currently on the server

determine which is the closest using  Xp = x coord of player, xt is X coord of target etc
For loop through the list of playr entities returned. Get their x y z coordinates and comapre them to current player  
Sqrt(square(xp-xt)+square(yp-yt)+square(zp-zt))

Once you have the player object that is closest, take the coordinates of that playerObj and then compare it to 
current player 

Target X is Lower, target is West of Player,  
target X is Higher, Target is East of Player
Target Z is lower, target  is North of player
target Z is higher, Target is South of PLayer


tolerance for determining direction: + or - 10

Check the difference between thevalues to determine HOW North or South they are.

so abs(Xp-Xt) = toleracne to include the direction. East or West  

Check the difference between thevalues to determine HOW east or West they are.

so abs(Zp-Zt) = Tolerance to include Direction north or south

ex

Tolerence is 10
Player is at (10,0)
Target is at (-10,10)

X absolute difference is 20. We know because its less that target is west of player. So the word "West" 
  is included in the statemeent to the user of hecompass
Z absolute different is 10, which is within tolerance.  WE know the target is south of the player, but,
  becuase its within the tolerance range, it is not included i teh message to teh user of the compass.

Tell the using compass using PLayer which direction to go.

if the Target player is within 36 blocks of the PLayer using the compass, they target gets a 
"You feel like you are being watched" message if the PLayer uses the compass again

*/
