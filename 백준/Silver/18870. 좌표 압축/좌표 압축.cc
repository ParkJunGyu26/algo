#include <iostream>
#include <algorithm>
#include <unordered_map>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

	int n;
	cin >> n;

	int X[1000000] = {0};
	for (int i = 0; i < n; i++)
		cin >> X[i];
	
	int tmp[1000000] = {0};
	for (int i = 0; i < n; i++)
		tmp[i] = X[i];

	sort(tmp, tmp+n);

	unordered_map<int, int> m;
	int index = 0;
	for (int i = 0; i < n; i++) {
		if (m.find(tmp[i]) == m.end()) {
			m.insert(pair<int, int>(tmp[i], index++));
		}
	}

	for (int i = 0; i < n; i++)
		cout << m[X[i]] << " ";
}