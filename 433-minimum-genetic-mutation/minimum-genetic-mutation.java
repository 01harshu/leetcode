class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(endGene)) {
            return -1;
        }

        char[] genes = new char[]{'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(startGene);
        visited.add(startGene);

        int mutations = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(endGene)) {
                    return mutations;
                }

                char[] currArray = curr.toCharArray();
                for (int j = 0; j < currArray.length; j++) {
                    char originalChar = currArray[j];

                    for (char c : genes) {
                        if (c == originalChar) continue;

                        currArray[j] = c;
                        String nextGene = new String(currArray);

                        if (bankSet.contains(nextGene) && !visited.contains(nextGene)) {
                            visited.add(nextGene);
                            queue.offer(nextGene);
                        }
                    }

                    currArray[j] = originalChar;
                }
            }

            mutations++;
        }

        return -1;
    }
}