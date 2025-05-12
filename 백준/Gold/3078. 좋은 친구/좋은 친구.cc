#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

long long answer = 0;
int n, k;
vector<long long> info(21);
vector<string> vec;

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	cin >> n >> k;
	vec.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i];

	int target = vec[0].size();
	for (int i = 0; i <= k; i++) info[vec[i].size()]++;

	int index = k+1;
	answer += info[target]-1;
	for (int i = 1; i < n-1; i++) {
		info[target]--;
		target = vec[i].size();
		if (index < n) {
			info[vec[i+k].size()]++;
			index++;
		}
		answer += info[target]-1;
	}

	cout << answer;
}