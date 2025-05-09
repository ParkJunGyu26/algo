#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
long answer = 0;
vector<pair<long, long>> vec;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vec.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i].first >> vec[i].second;

	sort(vec.begin(), vec.end());

	answer += abs(vec[0].second - vec[0].first);
	long before_end = vec[0].second;

	for (int i = 1; i < n; i++) {
		long start = vec[i].first;
		long end = vec[i].second;
		
		if (end <= before_end) continue;
		
		if (start <= before_end) answer += abs(end - before_end);
		else answer += abs(end - start);
	
		before_end = end;
		
	}

	cout << answer;
}