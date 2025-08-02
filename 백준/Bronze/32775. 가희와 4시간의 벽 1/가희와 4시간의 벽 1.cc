#include <iostream>
using namespace std;

int main() {
    int S_ab, F_ab;
    cin >> S_ab >> F_ab;

    if (S_ab <= F_ab) {
        cout << "high speed rail" << endl;
    } else {
        cout << "flight" << endl;
    }

    return 0;
}
