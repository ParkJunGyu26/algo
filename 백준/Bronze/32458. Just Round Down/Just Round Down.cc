#include <iostream>

int main() {
    double x;
    std::cin >> x;
    long long floor_x = static_cast<long long>(x);
    std::cout << floor_x << std::endl;
    return 0;
}