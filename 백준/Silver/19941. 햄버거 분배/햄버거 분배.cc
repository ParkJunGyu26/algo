#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

using namespace std;

int n, k;
string target;
vector<bool> visited;

// O(N*K)
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> k >> target;
	visited.resize(n);
	int answer = 0;
	
	// 사람의 왼쪽 먼저 탐색
	for (int i = 0; i < n; i++) {
		if (target[i] == 'P') {
			for (int j = max(0, i-k); j <= min(n-1, i+k); j++) {
				if (target[j] == 'H' && !visited[j]) {
					visited[j] = true;
					answer++;
					break;
				}
			}
		}
	}

	visited.clear();
	int cnt = 0;
	// 사람의 오른쪽
	for (int i = 0; i < n; i++) {
		if (target[i] == 'P') {
			for (int j = min(n-1, i+k); j >= max(0, i-k); j--) {
				if (target[j] == 'H' && !visited[j]) {
					visited[j] = true;
					cnt++;
					break;
				}
			}
		}
	}

	cout << max(cnt, answer);
}