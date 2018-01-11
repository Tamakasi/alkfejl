/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import hu.elte.alkfejl.entity.Folder;
import hu.elte.alkfejl.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderAndTask {
    Folder folder;
    String deadline;
    String state;
    String description;
    int priority;
}
