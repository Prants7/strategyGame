package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.inputSockets;

public interface InputSocket<SaveType> {

    public boolean hasElement();

    public void setElement(SaveType newElement);

    public SaveType getElement();
}
