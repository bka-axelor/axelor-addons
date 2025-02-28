/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2021 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.redmine.service.batch;

import com.axelor.apps.base.service.administration.AbstractBatch;
import com.axelor.apps.redmine.service.imports.RedmineService;
import com.axelor.apps.redmine.service.imports.common.RedmineImportCommonService;
import com.google.inject.Inject;

public class BatchImportAllRedmineIssue extends AbstractBatch {

  @Inject private RedmineService redmineService;

  @Override
  protected void process() {

    redmineService.redmineImportIssues(
        batch, ticket -> incrementDone(), error -> incrementAnomaly());
  }

  @Override
  protected void stop() {
    super.stop();
    String comments = RedmineImportCommonService.result;
    addComment(comments);
  }
}
