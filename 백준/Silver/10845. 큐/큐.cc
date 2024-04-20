#include <iostream>
#include <string>

using namespace std;
#define MAX_QUEUE_SIZE 10000

class Queue
{
private:
    int front;
    int rear;
    int data[MAX_QUEUE_SIZE];

public:
    Queue() {
        front = rear = -1;
    };

    ~Queue() {};

    void Push(int e) {
        data[++rear] = e;
    };

    int Pop() {
        if (Empty()) return -1;
    
        return data[++front];
    };

    int Size() {
        return (rear - front);
    };
    
    int Empty() {
        if (front == rear) {
            return 1;
        }
        return 0;
    };

    int Front() {
        if (Empty()) return -1;

        return data[front+1];
    };

    int Back() {
        if (Empty()) return -1;

        return data[rear];
    };

    void display() {
        if (!Empty()) {
            for (int i = front+1; i <= rear; i++) {
                cout << data[i] << " ";
            }
            cout << endl;
        }
    }

};

int main() {
    Queue queue;
    int n;
    cin >> n;
    
    for (int i = 0; i < n; i++) {
        string M;
        int num;

        cin >> M;

        if (M == "push") {
            cin >> num;
            queue.Push(num);
        }

        if (M == "pop") {
            cout << queue.Pop() << endl;
        }

        if (M == "size"){
            cout << queue.Size() << endl;
        }
        if (M == "empty")
            cout << queue.Empty() << endl;
        
        if (M == "front")
            cout << queue.Front() << endl;

        if (M == "back")
            cout << queue.Back() << endl;
    }
}