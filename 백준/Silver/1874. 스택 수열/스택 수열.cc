#include <iostream>
#include <stack>
#include <vector>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	vector<char> answer;
	int n, number;
	cin >> n;

	stack<int> s;
	int top = 0;
	s.push(top);

	for (int i = 0; i < n; i++) {
		cin >> number;
		while (top < number) {
			top++;
			s.push(top);
			answer.push_back('+');
		}
		if (s.top() == number) {
			s.pop();
			answer.push_back('-');
		}
	}

	if (s.size() != 1) cout << "NO";
	else {
		for (auto a : answer)
			cout << a << "\n";
	}
}