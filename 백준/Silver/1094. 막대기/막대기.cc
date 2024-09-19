#include <iostream>
#include <vector>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int x;
	cin >> x;

	vector<int> bin;
	while (true) {
		bin.push_back(x%2);
		if (x == 1) break;
		x /= 2;
	}

	int ans = 0;
	for (int i = 0; i < bin.size(); i++)
		if (bin[i] == 1) ans++;
	
	cout << ans;
}