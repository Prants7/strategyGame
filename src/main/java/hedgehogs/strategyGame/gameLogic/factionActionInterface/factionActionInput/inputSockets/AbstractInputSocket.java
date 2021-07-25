package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.inputSockets;

public abstract class AbstractInputSocket<SaveType> implements InputSocket<SaveType> {
    private SaveType savedElement;

    @Override
    public boolean hasElement() {
        return this.savedElement != null;
    }

    @Override
    public void setElement(SaveType newElement) {
        this.savedElement = newElement;
    }

    @Override
    public SaveType getElement() {
        return this.savedElement;
    }
}
