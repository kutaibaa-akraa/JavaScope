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

public class cmd_scope_slew_match_object_name extends cmdScopeObjectBase implements cmdScope {
    public boolean parseCmd(StringTokenizer st) {
        if (parseDirObject(st))
            return parseSecondsAndComment(st);
        return false;
    }

    /**
     *taken from class findObjectInFileSet;
     *can't use findObjectInFileSet as is because of the need to execute a method as soon as an object is found
     */
    public boolean process(cmdScopeList cl) {
        super.process(cl);
        int ix;
        position p = new position("cmd_scope_slew_match_object_name");
        dataFile df = new dataFile();
        fileFilter dataFileFilter = new fileFilter(eString.DAT_EXT);
        File files = new File(dir);
        File[] fileList;

        if (files != null) {
            fileList = files.listFiles(dataFileFilter);
            if (fileList != null)
                for (ix = 0; ix < fileList.length; ix++)
                    if (!fileList[ix].isDirectory()) {
                        df.loadFromFile(fileList[ix].toString());
                        if (df.findObject(object, p)) {
                            cl.t.copyPosToInAndTargetThenTurnTrackingOn(p);
                            return true;
                        }
                    }
        }
        return false;
    }
}

