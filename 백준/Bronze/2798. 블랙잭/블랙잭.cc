#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
	int n, m;
	cin >> n >> m;

	vector<int> card;
	
	for (int i = 0; i < n; i++) {
		int num;
		cin >> num;
		card.push_back(num);
	}

	int ans = 0;

	for (int i = 0; i < n-2; i++) {
		for (int j = i+1; j < n-1; j++) {
			for (int z = j+1; z < n; z++) {
				int target = card[i] + card[j] + card[z];
				if (target <= m) ans = max(ans, target);
			}
		}
	}
	
	cout << ans;

	return 0;
}