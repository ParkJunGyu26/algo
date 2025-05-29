import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static ArrayList<Integer> answer;
    static PriorityQueue<Course> pq;

    static class Course {
        int index, start, end;

        Course(int index, int start, int end) {
            this.index = index;
            this.start = start;
            this.end = end;
        }
    }

    static class Room {
        int roomId, courseId, start, end;

        Room(int roomId, int courseId, int start, int end) {
            this.roomId = roomId;
            this.courseId = courseId;
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        answer = new ArrayList<>();
        for (int i = 0; i <= n; i++) answer.add(0);

        pq = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.start, c2.start));

        for (int i = 1; i <= n; i++) {
            int index, start, end;
            st = new StringTokenizer(br.readLine());

            index = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            pq.offer(new Course(index, start, end));
        }

        int roomCnt = 1;
        PriorityQueue<Room> rooms = new PriorityQueue<>((r1, r2) -> Integer.compare(r1.end, r2.end));
        rooms.offer(new Room(1, 0, 0, 0));

        while (!pq.isEmpty() && !rooms.isEmpty()) {
            Course course = pq.poll();
            Room room = rooms.poll();

            if (room.end <= course.start) {
                rooms.offer(new Room(room.roomId, course.index , course.start, course.end));
                answer.set(course.index, room.roomId);
            } else {
                rooms.offer(new Room(roomCnt+1, course.index, course.start, course.end));
                rooms.offer(room);
                answer.set(course.index, ++roomCnt);
            }
        }

        bw.write(String.valueOf((roomCnt)) + "\n");
        for (int i = 1; i <= n; i++) bw.write(String.valueOf(answer.get(i)) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

// 오름차순