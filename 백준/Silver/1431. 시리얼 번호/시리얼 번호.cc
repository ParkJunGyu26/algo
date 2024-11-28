#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

using namespace std;

bool cmp(string &a, string &b) {
	if (a.size() != b.size())
		return a.size() < b.size();

	int a_s, b_s;
	a_s = b_s = 0;
	for (auto _ : a)
		if ('0' <= _ && _ <= '9') a_s += _ - '0';
	
	for (auto _ : b)
		if ('0' <= _ && _ <= '9') b_s += _ - '0';

	if (a_s != b_s)
		return a_s < b_s;

	return a < b;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;

	cin >> n;
	vector<string> vec(n);
	for (int _ = 0; _ < n; _++) {
		cin >> vec[_];
	}

	sort(vec.begin(), vec.end(), cmp);
	for (auto v : vec)
		cout << v << "\n";
}