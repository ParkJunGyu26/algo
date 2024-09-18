#include <iostream>
#include <string>
#include <unordered_map>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
	
	int n, m;
	cin >> n >> m;

	unordered_map<string, int> M1;
	unordered_map<int, string> M2;
	string tmp;
	for (int i = 1; i <= n; i++) {
		cin >> tmp;
		M1.insert(make_pair(tmp, i));
		M2.insert(make_pair(i, tmp));
	}

	for (int i = 0; i < m; i++) {
		cin >> tmp;
		if ('0' <= tmp[0] && tmp[0] <= '9') {
			cout << M2[stoi(tmp)] << '\n';
		} else {
			cout << M1[tmp] << '\n';
		}
	}
}