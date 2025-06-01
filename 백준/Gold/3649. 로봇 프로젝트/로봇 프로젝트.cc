#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

pair<int, int> answer;
int x, n;
vector<int> L;

// 1센티 = 10^7나노미터
int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	while (cin >> x) {
		cin >> n;
		x *= 10000000;
		L.resize(n);
		for (int i = 0; i < n; i++) cin >> L[i];
		sort(L.begin(), L.end());
	
		answer = {0, 0};
		int left = 0;
		int right = n-1;
	
		while (left < right) {
			int sum = L[left] + L[right];
	
			if (sum == x) {
				answer = {L[left], L[right]};
				break;
			} else if (sum < x) {
				left++;
			} else {
				right--;
			}
		}
	
		if (answer.first == 0 && answer.second == 0) cout << "danger";
		else cout << "yes " << answer.first << " " << answer.second;
		cout << "\n";
	}
}