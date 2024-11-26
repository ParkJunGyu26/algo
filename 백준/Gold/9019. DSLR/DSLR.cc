#include <iostream>
#include <vector>
#include <queue>
#include <string>

using namespace std;

int t, a, b;
char order[4] = {'D', 'S', 'L', 'R'};

int rotate(int dire, int number) {
	int four = number / 1000;
	int three = (number % 1000) / 100;
	int two = (number % 100) / 10;
	int one = (number % 10);
	// 왼쪽
	if (dire == 0) return three*1000 + two*100 + one*10 + four;

	// 오른쪽
	return one*1000 + four*100 + three*10 + two;
}

void bfs() {
	queue<pair<int, string>> q;
	q.push({a, ""});
	int visited[10000] = {0};
	visited[a] = 1;

	while(!q.empty()) {
		int x = q.front().first;
		string now = q.front().second;
		q.pop();

		if (x == b) {
			cout << now << "\n";
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx;
			
			if (i == 0) nx = (2*x)%10000;
			else if (i == 1) {
				nx = x-1;
				if (nx < 0) nx = 9999;
			}
			else if (i == 2) nx = rotate(0, x);
			else nx = rotate(1, x);

			if (visited[nx] == 1) continue;

			if (nx == b) {
				cout << now + order[i] << "\n";
				return;
			}

			q.push({nx, now + order[i]});
			visited[nx] = 1;
		}
	}
}

int main() {
	cin >> t;
	for (int _ = 0; _ < t; _++) {
		cin >> a >> b;
		bfs();
	}
}