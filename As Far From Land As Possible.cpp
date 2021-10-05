/*
LEETCODE - 1162. As Far from Land as Possible
Medium
Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.



Example 1:


Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:


Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/


class Solution {
public:

    int maxDistance(vector<vector<int>>& g) {

        queue<vector<int>> q;

        for (int i = 0; i < g.size(); i++) {
            for (int j = 0; j < g[0].size(); j++) {
                if (g[i][j] == 1) {
                    q.push({i, j, 0});
                }
                else {
                    g[i][j] = -1;
                }
            }
        }

        if (q.empty() || q.size() == g.size()*g[0].size())
            return -1;

        int res = 0;

        while (!q.empty()) {

            int len = q.size();

            while (len--) {

                auto v = q.front();

                q.pop();

                int x = v[0], y = v[1], d = v[2];

                res = max(res, d);

                if (x > 0 && g[x - 1][y] == -1) {
                    g[x - 1][y] = d + 1;
                    q.push({x - 1, y, d + 1});
                }

                if (y > 0 && g[x][y - 1] == -1) {
                    g[x][y - 1] = d + 1;
                    q.push({x, y - 1, d + 1});
                }

                if (x < g.size() - 1 && g[x + 1][y] == -1) {
                    g[x + 1][y] = d + 1;
                    q.push({x + 1, y, d + 1});
                }

                if (y < g[0].size() - 1 && g[x][y + 1] == -1) {
                    g[x][y + 1] = d + 1;
                    q.push({x, y + 1, d + 1});
                }
            }
        }

        return res;
    }
};