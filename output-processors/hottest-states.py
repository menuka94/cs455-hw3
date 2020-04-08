input = open("../cluster-outputs/hottest-states")

lines = input.readlines()
states = []
temperatures = []

for line in lines:
    line = line.split()
    states.append(line[:-1])
    temperatures.append(float(line[-1]))

for i in range(len(states)):
    states[i] = " ".join(states[i])

top10_temperatures = sorted(temperatures)[::-1][0:10]

for t in top10_temperatures:
    state = states[temperatures.index(t)]
    print('  - ' + state)