/*
 * Copyright 2025-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author yHong
 */

package com.alibaba.example.graph.node;

import com.alibaba.cloud.ai.graph.OverAllState;
import com.alibaba.cloud.ai.graph.action.NodeAction;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yHong
 * @version 1.0
 * @since 2025/4/24 16:03
 */
public class TitleGeneratorNode implements NodeAction {

	private final ChatClient chatClient;

	public TitleGeneratorNode(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@Override
	public Map<String, Object> apply(OverAllState state) {
		String content = (String) state.value("reworded").orElse("");
		String prompt = "请为以下内容生成一个简洁有吸引力的中文标题：\n\n" + content;

		ChatResponse response = chatClient.prompt(prompt).call().chatResponse();
		String title = response.getResult().getOutput().getText();

		Map<String, Object> result = new HashMap<>();
		result.put("title", title);
		return result;
	}

}
