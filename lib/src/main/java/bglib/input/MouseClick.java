package bglib.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

public class MouseClick implements MouseListener {

    Set<Integer> buttonsPressed;

    public MouseClick() {
        super();

        buttonsPressed = new HashSet<Integer>();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        setClicked(event.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        setReleased(event.getButton());
    }

    public boolean getButtonPressed(int button) {
        if (button >= 0 && button <= 3)
            return buttonsPressed.contains(button);
        
        return false;
    }

    public boolean anyButtonsPressed() {
        return buttonsPressed.size() != 0;
    }

    private void setClicked(int button) {
        if (button >= 0 && button <= 3)
            buttonsPressed.add(button);
    }

    private void setReleased(int button) {
        if (button >= 0 && button <= 3)
            buttonsPressed.remove(button);
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void mouseClicked(MouseEvent event) {} 
}
