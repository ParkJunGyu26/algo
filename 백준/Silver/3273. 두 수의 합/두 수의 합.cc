#include <iostream>
#include <vector>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int answer = 0;
	int n, tmp, x;
	int visited[1000001] = {0};
	int arr[1000001] = {0};
	vector<int> hubo;

	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		arr[tmp]++;
		hubo.push_back(tmp);
	}

	cin >> x;
	for (int i = 0; i < n; i++) {
		if (x-hubo[i] <= 0 || x-hubo[i] > 1000000) continue;

		if (arr[x-hubo[i]] == 1 && visited[x-hubo[i]] == 0 && hubo[i] != x-hubo[i]) {
			visited[x-hubo[i]] = visited[hubo[i]] = 1;
			answer++;
		}
	}

	cout << answer;
}