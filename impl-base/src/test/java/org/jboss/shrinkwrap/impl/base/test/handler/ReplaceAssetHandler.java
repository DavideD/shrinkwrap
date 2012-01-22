/*
 * JBoss, Home of Professional Open Source
 * Copyright ${year}, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.shrinkwrap.impl.base.test.handler;

import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ArchiveEvent;
import org.jboss.shrinkwrap.api.Handler;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.StringAsset;

public class ReplaceAssetHandler implements Handler {

   public final Asset returnedAsset;

   public Asset savedAsset;

   public ArchivePath savedPath;

   public boolean called = false;

   public ReplaceAssetHandler(String assetContent) {
      this.returnedAsset = new StringAsset(assetContent);
   }

   @Override
   public Asset handle(ArchiveEvent event) {
      this.savedAsset = event.getAsset();
      this.savedPath = event.getPath();
      this.called = true;
      return returnedAsset;
   }

}