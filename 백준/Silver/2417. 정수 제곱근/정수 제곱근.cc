#include <iostream>
#include <cmath>

#define ll long long

using namespace std;

int main() {
	ll n;
	cin >> n;
	
	ll left = 0;
	ll right = n;
	ll mid = 0;

	ll ans = 0;
	// mid 후보 중 제일 왼쪽(매변탐, bisect_lower)
	while (left <= right) {
		mid = (left + right) / 2;
		
		if (pow(mid, 2) >= n) {
			ans = mid;
			right = mid - 1;
		} else {
			left = mid + 1;
		}
	}

	cout << ans;
}