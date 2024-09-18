#include <iostream>
#include <set>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	set<int> s;
	int cnt = 0;

	int a, b;
	cin >> a >> b;

	for (int i = 0; i < a+b; i++) {
		int tmp;
		cin >> tmp;

		if (s.find(tmp) == s.end()) s.insert(tmp); 
		else cnt++;
	}

	cout << s.size()-cnt;
}