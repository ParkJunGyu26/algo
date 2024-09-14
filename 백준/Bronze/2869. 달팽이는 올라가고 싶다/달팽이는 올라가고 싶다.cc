#include <iostream>

using namespace std;

int main() {
	int A, B, V;
	cin >> A >> B >> V;

	int a1 = A;
	int d = A-B;
	if ((V-A) % (A-B) == 0) {
		cout << (int)((V - A) / (A - B)) + 1;
	} else {
		cout << (int)((V - A) / (A - B)) + 2;
	}
}