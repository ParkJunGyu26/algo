#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <algorithm>

using namespace std;

int t, f;
vector<int> parent, friend_num;
unordered_map<string, int> um;

int find(int node) {
	if (node == parent[node]) return node;

	return parent[node] = find(parent[node]);
}

void merge(int x, int y) {
	x = find(x);
	y = find(y);
	if (x == y) return;

	if (x < y) {
		parent[y] = x;
		friend_num[x] += friend_num[y];
	} else {
		parent[x] = y;
		friend_num[y] += friend_num[x];
	}
}

void initParent(int num) {
	parent.assign(num * 2 + 1, 0);
	friend_num.assign(num * 2 + 1, 1);

	for (int i = 0; i <= num * 2; i++) parent[i] = i;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> t;
	for (int _ = 0; _ < t; _++) {
		um.clear();

		cin >> f;
		initParent(f);

		int num = 1;
		for (int i = 0; i < f; i++) {
			string a, b;
			cin >> a >> b;

			if (um.find(a) == um.end()) {
				um[a] = num;
				parent[um[a]] = num++;
			}
			if (um.find(b) == um.end()) {
				um[b] = num;
				parent[um[b]] = num++;
			}

			merge(um[a], um[b]);

			int target = find(um[a]);
			// cout << "target : " << target << "\n";
			// cout << "um[a] : " << um[a] << "\n";
			// cout << "~~~~\n";

			cout << friend_num[target] << "\n";
		}
	}
}