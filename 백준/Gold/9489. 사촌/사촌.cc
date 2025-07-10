#include <iostream>
#include <unordered_map>
#include <vector>
#include <queue>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, k;
    while (cin >> n >> k) {
        if (n == 0 && k == 0) break;

        vector<int> arr(n);
        for (int i = 0; i < n; ++i) cin >> arr[i];

        unordered_map<int, int> parent;
        unordered_map<int, vector<int>> children;

        queue<int> leaf_candidates;
        parent[arr[0]] = 0;
        leaf_candidates.push(arr[0]);

        int i = 1;
        while (i < n) {
            int p = leaf_candidates.front();
            vector<int> group;

            group.push_back(arr[i++]);
            while (i < n && arr[i-1] + 1 == arr[i]) {
                group.push_back(arr[i++]);
            }

            for (int node : group) {
                parent[node] = p;
                leaf_candidates.push(node);
                children[p].push_back(node);
            }
            leaf_candidates.pop();
        }

        int pk = parent[k];
        int gpk = parent[pk]; 
        int cousin_count = 0;

        if (gpk != 0) {
            for (int sibling_parent : children[gpk]) {
                if (sibling_parent == pk) continue;
                cousin_count += children[sibling_parent].size();
            }
        }

        cout << cousin_count << '\n';
    }
    return 0;
}
