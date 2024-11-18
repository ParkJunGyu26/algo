#include <iostream>
#include <queue>
#include <string>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n, num;
	string str;
	queue<int> q;

	cin >> n;
	
	for (int i = 0; i < n; i++) {
		cin >> str;
		if (str == "push") {
			cin >> num;
			q.push(num);
			continue;
		}
		
		else if (str == "front") {
			if (!q.empty()) cout << q.front();
			else cout << -1;
		}
		else if (str == "back") {
			if (!q.empty()) cout << q.back();
			else cout << -1;
		}
		else if (str == "size") cout << q.size();
		else if (str == "empty") {
			if (!q.empty()) cout << 0;
			else cout << 1;
		}
		else {
			if (!q.empty()) {
				cout << q.front();
				q.pop();
			} else cout << -1;
		}
		cout << "\n";
	}
}