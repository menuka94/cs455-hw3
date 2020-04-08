input = open("../cluster-outputs/hourly-so2-levels")
lines = input.readlines()
times = []
so2_levels = []

for line in lines:
    splits = line.split()
    times.append(splits[0])
    so2_levels.append(splits[1])

highest = max(so2_levels)
time = times[so2_levels.index(highest)]
print(time)