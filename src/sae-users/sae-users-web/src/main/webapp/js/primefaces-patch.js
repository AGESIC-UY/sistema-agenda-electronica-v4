if (PrimeFaces.widget.DataTable) {
    PrimeFaces.widget.DataTable = PrimeFaces.widget.DataTable.extend({
        tabCell: function (cell, forward) {
            var targetCell = forward ? cell.next('td.ui-editable-column') : cell.prev('td.ui-editable-column');
            if(targetCell.length == 0) {
                var tabRow = forward ? cell.parent().next() : cell.parent().prev();
                targetCell = forward ? tabRow.children('td.ui-editable-column:first') : tabRow.children('td.ui-editable-column:last');
            }
                                       
            this.showCellEditor(targetCell);
            

//            var targetCell = forward ? cell.nextAll('td.ui-editable-column:first') : cell.prevAll('td.ui-editable-column:first');
//            if (targetCell.length == 0) {
//                var tabRow = forward ? cell.parent().next() : cell.parent().prev();
//                targetCell = forward ? tabRow.children('td.ui-editable-column:first') : tabRow.children('td.ui-editable-column:last');
//            }
//
//            var cellEditor = targetCell.children('div.ui-cell-editor'),
//                    inputContainer = cellEditor.children('div.ui-cell-editor-input');
//
//            if (inputContainer.length) {
//                var inputs = inputContainer.find(':input'),
//                        disabledInputs = inputs.filter(':disabled');
//
//                if (inputs.length === disabledInputs.length) {
//                    this.tabCell(targetCell, forward);
//                    return;
//                }
//            }

            
        }
    });
}

