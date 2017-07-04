import java.awt.*;
import java.util.Random;

public class AlgoVisualizer {

    private int N;
    private int[] numbers;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, AlgoFrame frame){
        this.N = N;
        this.frame = frame;

        numbers = new int[N];

        // 根据frame的大小计算合理数值
        Random rand = new Random();
        for( int i = 0 ; i < N ; i ++)
            numbers[i] = rand.nextInt(800) + 1;

    }

    public void run(){

        frame.setNumbers(numbers);
        AlgoVisHelper.pause(20);
        for( int i = 0 ; i < N ; i ++ ){
            // 寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            for( int j = i + 1 ; j < N ; j ++ )
                // 使用compareTo方法比较两个Comparable对象的大小
                if( numbers[j] < numbers[minIndex] )
                    minIndex = j;

            swap( numbers , i , minIndex);
            frame.setNumbers(numbers);
            AlgoVisHelper.pause(100);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Selection Visualization", sceneWidth,sceneHeight);

            int N = 200;
            AlgoVisualizer vis = new AlgoVisualizer(N, frame);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}