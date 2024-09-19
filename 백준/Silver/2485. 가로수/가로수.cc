#include <iostream>
#include <set>
#include <vector>
#include <algorithm>

using namespace std;

int max(int a, int b) {
	if (a > b) return a;
	return b;
}

int min(int a, int b) {
	if (a > b) return b;
	return a;
}

int gcd(int a, int b) {
	int MAX = max(a, b);
	int MIN = min(a, b);
	int tmp;

	while (true) {
		tmp = MAX % MIN;
		if (tmp == 0) {
			return MIN;
		}
		MAX = MIN;
		MIN = tmp;
	}
}

// 최소공약수 찾기
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;

	vector<int> A;
	for (int i = 0; i < n; i++) {
		int number;
		cin >> number;
		A.push_back(number);
	}
	
	set<int> s;
	for (int i = 1; i < n; i++)
		s.insert(A[i] - A[i-1]);

	vector<int> num;
	for (auto a : s)
		num.push_back(a);

	int g;
	if (num.size() < 2) g = num[0];
	else {
		g = gcd(num[0], num[1]);
		if (num.size() > 2) {
			for (int i = 2; i < num.size(); i++) {
				g = gcd(g, num[i]);
			}
		}
	}

	cout << (A[n-1] - A[0])/g -1-(n-2);
}