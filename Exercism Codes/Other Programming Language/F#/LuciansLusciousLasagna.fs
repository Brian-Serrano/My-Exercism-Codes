module LuciansLusciousLasagna

let expectedMinutesInOven = 40

let remainingMinutesInOven x : int = expectedMinutesInOven - x

let preparationTimeInMinutes x : int = x * 2

let elapsedTimeInMinutes x y : int = y + preparationTimeInMinutes x