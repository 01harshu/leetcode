
class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }

        int maxPoints = 0;

        for (int i = 0; i < n; i++) {
            Map<Double, Integer> slopeCount = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                double slope;
                if (points[i][0] == points[j][0]) {
                    slope = Double.POSITIVE_INFINITY;
                } else {
                    slope = (double) (points[j][1] - points[i][1]) / (points[j][0] - points[i][0]);
                    if (slope == -0.0) {
                        slope = 0.0;
                    }
                }

                slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1);
                maxPoints = Math.max(maxPoints, slopeCount.get(slope) + 1);
            }
        }

        return maxPoints;
    }
}