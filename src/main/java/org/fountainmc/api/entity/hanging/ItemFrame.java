package org.fountainmc.api.entity.hanging;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.data.EntityData;
import org.fountainmc.api.entity.data.hanging.ItemFrameData;

/**
 * An ItemFrame that is hanging on a wall
 */
@NonnullByDefault
public interface ItemFrame extends HangingEntity, ItemFrameData {

    /**
     * Copy all of the given data to this item frame.
     * <p>Doesn't copy passenger information.</p>
     *
     * @param data the data to copy from
     */
    @Override
    default void copyDataFrom(EntityData data) {
        ItemFrameData.super.copyDataFrom(data);
    }

    /**
     * Take a snapshot of this entity's data
     * <p>The resulting snapshot is thread-safe.</p>
     *
     * @return a snapshot
     */
    @Override
    ItemFrameData snapshot();

}
