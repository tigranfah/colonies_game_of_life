package core;

public class Renderer {

    private float renderingSpeed = 0.5f;

    public Renderer() { }

    public Renderer(float renderingSpeed) {
        this.renderingSpeed = renderingSpeed;
    }

    private void renderCell(Cell cell) {
        System.out.printf("%s ", cell.toString());
    }

    public void render(Board board) {
        for (int i = 0; i < board.getHeight(); ++i) {
            for (int j = 0; j < board.getWidth(); ++j) {
                this.renderCell(board.getCellAt(new Position(j, i)));
            }
            System.out.printf("\n");
        }
    }

    // clear the output of the screen to get smooth running console
    public static void clearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

}