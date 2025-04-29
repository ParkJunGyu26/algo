#include <iostream>
#include <vector>

using namespace std;

int n, k;
int answer = 0;
vector<int> vec;

void dfs(vector<int> &hubo, vector<bool> &visited, int weight) {
	if (hubo.size() == n) {
		answer++;
		return;
	}

	for (int i = 0; i < n; i++) {
		if (!visited[i]) {
			int nWeight = weight + vec[i] - k;
			if (nWeight < 500) continue;

			hubo.push_back(i);
			visited[i] = true;

			dfs(hubo, visited, nWeight);

			visited[i] = false;
			hubo.pop_back();
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> k;
	vec.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i];

	vector<bool> visited(n);
	vector<int> hubo;
	dfs(hubo, visited, 500);

	cout << answer;
}