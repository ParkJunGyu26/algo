#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
	ios_base :: sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n, ans;
	cin >> n;


	ans = 1e9;
	for (int i = 0; i < n; i++) {
		int a, b;
		cin >> a >> b;
		if (a <= b) ans = min(ans, b);
	}

	if (ans == 1e9) cout << -1;
	else cout << ans;

	return 0;
}