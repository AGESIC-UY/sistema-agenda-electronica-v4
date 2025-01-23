$(document).ready(function () {
        //Fix for primefaces bug: https://github.com/primefaces/primefaces/issues/3437
        PrimeFaces.widget.DataTable.prototype.showCurrentCell = function (cell) {
            var $this = this;

            if (this.currentCell) {
                if (this.cfg.saveOnCellBlur)
                    this.saveCell(this.currentCell);
                else if (!this.currentCell.is(cell))
                    this.doCellEditCancelRequest(this.currentCell);
            }
            if (cell && cell.length) {
                this.currentCell = cell;

                var cellEditor = cell.children('div.ui-cell-editor'),
                    displayContainer = cellEditor.children('div.ui-cell-editor-output'),
                    inputContainer = cellEditor.children('div.ui-cell-editor-input'),
                    inputs = inputContainer.find(':input:enabled'),
                    multi = inputs.length > 1;

                cell.addClass('ui-state-highlight ui-cell-editing');
                displayContainer.hide();
                inputContainer.show();
                inputs.eq(0).focus().select();

                //metadata
                if (multi) {
                    var oldValues = [];
                    for (var i = 0; i < inputs.length; i++) {
                        var input = inputs.eq(i);

                        if (input.is(':checkbox')) {
                            oldValues.push(input.val() + "_" + input.is(':checked'));
                        } else {
                            oldValues.push(input.val());
                        }
                    }

                    cell.data('multi-edit', true);
                    cell.data('old-value', oldValues);
                } else {
                    cell.data('multi-edit', false);
                    cell.data('old-value', inputs.eq(0).val());
                }

                //bind events on demand
                if (!cell.data('edit-events-bound')) {
                    cell.data('edit-events-bound', true);

                    inputs.on('keydown.datatable-cell', function (e) {
                        var keyCode = $.ui.keyCode,
                            shiftKey = e.shiftKey,
                            key = e.which,
                            input = $(this);

                        if (key === keyCode.ENTER || key == keyCode.NUMPAD_ENTER) {
                            $this.saveCell(cell);

                            e.preventDefault();
                        } else if (key === keyCode.TAB) {
                            if (multi) {
                                var focusIndex = shiftKey ? input.index() - 1 : input.index() + 1;

                                if (focusIndex < 0 || (focusIndex === inputs.length) || input.parent().hasClass('ui-inputnumber') || input.parent().hasClass('ui-helper-hidden-accessible')) {
                                    $this.tabCell(cell, !shiftKey);
                                } else {
                                    inputs.eq(focusIndex).focus();
                                }
                            } else {
                                $this.tabCell(cell, !shiftKey);
                            }

                            e.preventDefault();
                        } else if (key === keyCode.ESCAPE) {
                            $this.doCellEditCancelRequest(cell);
                            e.preventDefault();
                        }
                    })
                        .on('focus.datatable-cell click.datatable-cell', function (e) {
                            $this.currentCell = cell;
                        });
                }
            } else {
                this.currentCell = null;
            }
        }
        //showCurrentCell END
    });