import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.colorchooser.*;
import javax.swing.filechooser.*;
import javax.accessibility.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.net.*;

public class cmd_scope_slew_abs_equat extends cmdScopeEquatBase implements cmdScope {
    public boolean parseCmd(StringTokenizer st) {
        if (parseEquat(st)) {
            parseMoveTimeSec(st);
            return parseSecondsAndObjectName(st);
        }
        return false;
    }

    public boolean process(cmdScopeList cl) {
        super.process(cl);
        cl.t.copyPosToInAndTargetThenTurnTrackingOn(p);
        if (moveTimeSec > 0.)
            equatMoveOverTime();
        return true;
    }
}

