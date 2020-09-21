package ru.afanasev.diplom.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.afanasev.diplom.object.dto.mapper.TagMapper;
import ru.afanasev.diplom.object.dto.tagDtos.TagDto;
import ru.afanasev.diplom.object.dto.tagDtos.TagDtoResponse;
import ru.afanasev.diplom.object.repository.PostRepository;
import ru.afanasev.diplom.object.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public TagDtoResponse getAllweight(String tag) {
		TagDtoResponse tagDtoResponse = new TagDtoResponse();
		List<Double> listWeight = new ArrayList<>();
		getWeight(listWeight, tag);
		Collections.sort(listWeight);
		Double maxValue = listWeight.get(listWeight.size() - 1);
		tagDtoResponse.setTags(getListTagDto(maxValue, tag));
		return tagDtoResponse;
		

	}

	private List<String> getAllName(String tag) {
		if (tag == null) {
			return tagRepository.findAllNames();
		}

		return tagRepository.findAllNamesByQuery(tag);
	}

	private void getWeight(List<Double> listWeight, String tag) {
		for (String tagName : getAllName(tag)) {
			System.out.println(postRepository.findWeightByTag(tagName));
			listWeight.add(postRepository.findWeightByTag(tagName));

		}
	}

	private Set<TagDto> getListTagDto(Double maxValue, String tag) {
		Set<TagDto> list = new LinkedHashSet<>();
		for (String tagName : getAllName(tag)) {
			list.add(TagMapper.entityToTagDto(tagName, postRepository.findWeightByTag(tagName) / maxValue));
		}
		
		return list;
	}

}
