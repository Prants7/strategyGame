package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.inputSockets;

public class InputSocketImp<SaveType> implements InputSocket<SaveType> {
    private SaveType savedElement;

    public InputSocketImp() {
    }

    public InputSocketImp(SaveType elementToSave) {
        this.savedElement = elementToSave;
    }

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
