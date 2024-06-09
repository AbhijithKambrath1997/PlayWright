package uiUtil;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;

import java.util.List;

public class FrameUtil {

    /**
     * Switches to a frame by its name or id.
     *
     * @param page   The page to switch frames on.
     * @param frameNameOrId The name or id of the frame to switch to.
     * @return The frame object if found, otherwise null.
     */
    public Frame switchToFrameByNameOrId(Page page, String frameNameOrId) {
        return page.frame(frameNameOrId);
    }

    /**
     * Switches to a frame by its index.
     *
     * @param page  The page to switch frames on.
     * @param index The index of the frame to switch to.
     * @return The frame object if found, otherwise null.
     */
    public Frame switchToFrameByIndex(Page page, int index) {
        List<Frame> frames = page.frames();
        if (index >= 0 && index < frames.size()) {
            return frames.get(index);
        }
        return null;
    }

    /**
     * Switches back to the parent frame.
     *
     * @param page The page to switch frames on.
     */
    public void switchToParentFrame(Page page) {
        page.mainFrame();
    }

    /**
     * Switches to a frame by its selector.
     *
     * @param page     The page to switch frames on.
     * @param selector The selector of the frame to switch to.
     * @return The frame object if found, otherwise null.
     */
    public Frame switchToFrameBySelector(Page page, String selector) {
        ElementHandle frameElement = page.querySelector(selector);
        return frameElement.contentFrame();
    }

    /**
     * Switches to an iframe by its selector.
     *
     * @param page     The page to switch iframes on.
     * @param selector The selector of the iframe to switch to.
     * @return The frame object representing the iframe if found, otherwise null.
     */
    public Frame switchToIframeBySelector(Page page, String selector) {
        ElementHandle iframeElement = page.querySelector(selector);
        return iframeElement.contentFrame();
    }
}