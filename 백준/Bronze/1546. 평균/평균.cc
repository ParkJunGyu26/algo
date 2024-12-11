#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	int n;
	cin >> n;
	vector<double> test(n);
	vector<double> new_test;
	
	for (int i = 0; i < n; i++) cin >> test[i];

	double MAX = *max_element(test.begin(), test.end());
	// cout << "max : " << MAX << "\n";
	for (auto t : test) new_test.push_back( (t / MAX) * 100);

	double answer = 0;
	for (auto ne : new_test) answer += ne; //cout << "ne : " << ne << " "; //answer += ne;
	cout << answer/n;
}