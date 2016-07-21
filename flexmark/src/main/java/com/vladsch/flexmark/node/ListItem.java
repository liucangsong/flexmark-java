package com.vladsch.flexmark.node;

import com.vladsch.flexmark.internal.BlockContent;
import com.vladsch.flexmark.internal.util.sequence.BasedSequence;
import com.vladsch.flexmark.internal.util.sequence.SubSequence;

import java.util.List;

public abstract class ListItem extends Block {
    protected BasedSequence openingMarker = SubSequence.NULL;
    private Boolean tight = true;

    @Override
    public void getAstExtra(StringBuilder out) {
        segmentSpanChars(out, openingMarker, "open");
        if (isTight()) out.append(" isTight");
        else out.append(" isLoose");
    }

    @Override
    public BasedSequence[] getSegments() {
        return new BasedSequence[] { openingMarker };
    }

    public BasedSequence getOpeningMarker() {
        return openingMarker;
    }

    public void setOpeningMarker(BasedSequence openingMarker) {
        this.openingMarker = openingMarker;
    }

    public void setTight(boolean tight) {
        this.tight = tight;
    }

    public void setLoose(boolean loose) {
        this.tight = !loose;
    }

    public boolean isTight() {
        return tight && isInTightList();
    }

    public boolean isLoose() {
        return !isTight();
    }

    public boolean isInTightList() {
        return !(getParent() instanceof ListBlock) || ((ListBlock) getParent()).isTight();
    }

    public boolean isParagraphInTightListItem() {
        return isTight();
    }

    @Override
    public Node getLastBlankLineChild() {
        return getLastChild();
    }

    public ListItem() {
    }

    public ListItem(BasedSequence chars) {
        super(chars);
    }

    public ListItem(BasedSequence chars, List<BasedSequence> segments) {
        super(chars, segments);
    }

    public ListItem(BlockContent blockContent) {
        super(blockContent);
    }
}
