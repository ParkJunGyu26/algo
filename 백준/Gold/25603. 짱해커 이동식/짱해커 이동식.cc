#include <iostream>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

int n, k;
vector<int> vec;
vector<long long> hubo;
map<long, int> m;

int main() {
	ios_base::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);

	cin >> n >> k;
	vec.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i];

	for (int i = 0; i < k; i++) {
		if (m.find(vec[i]) == m.end()) m[vec[i]] = 1;
		else m[vec[i]]++;
	}
	hubo.push_back(m.begin()->first);

	for (int i = 0; i < n-k; i++) {
		if (m[vec[i]] == 1) m.erase(vec[i]);
		else m[vec[i]]--;

		if (m.find(vec[i+k]) == m.end()) m[vec[i+k]] = 1;
		else m[vec[i+k]]++;

		hubo.push_back(m.begin()->first);
	}

	sort(hubo.begin(), hubo.end());
	cout << hubo.back();
}